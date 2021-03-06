/*
 * ------------------------------------------------------------------------
 *
 *  Copyright by KNIME AG, Zurich, Switzerland
 *  Website: http://www.knime.org; Email: contact@knime.org
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License, Version 3, as
 *  published by the Free Software Foundation.
 *
 *  This program is distributed in the hope that it will be useful, but
 *  WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, see <http://www.gnu.org/licenses>.
 *
 *  Additional permission under GNU GPL version 3 section 7:
 *
 *  KNIME interoperates with ECLIPSE solely via ECLIPSE's plug-in APIs.
 *  Hence, KNIME and ECLIPSE are both independent programs and are not
 *  derived from each other. Should, however, the interpretation of the
 *  GNU GPL Version 3 ("License") under any applicable laws result in
 *  KNIME and ECLIPSE being a combined program, KNIME AG herewith grants
 *  you the additional permission to use and propagate KNIME together with
 *  ECLIPSE with only the license terms in place for ECLIPSE applying to
 *  ECLIPSE and the GNU GPL Version 3 applying for KNIME, provided the
 *  license terms of ECLIPSE themselves allow for the respective use and
 *  propagation of ECLIPSE together with KNIME.
 *
 *  Additional permission relating to nodes for KNIME that extend the Node
 *  Extension (and in particular that are based on subclasses of NodeModel,
 *  NodeDialog, and NodeView) and that only interoperate with KNIME through
 *  standard APIs ("Nodes"):
 *  Nodes are deemed to be separate and independent programs and to not be
 *  covered works.  Notwithstanding anything to the contrary in the
 *  License, the License does not apply to Nodes, you are not required to
 *  license Nodes under the License, and you are granted a license to
 *  prepare and propagate Nodes, in each case even if such Nodes are
 *  propagated with or for interoperation with KNIME.  The owner of a Node
 *  may freely choose the license terms applicable to such Node, including
 *  when such Node is propagated with or for interoperation with KNIME.
 * ---------------------------------------------------------------------
 *
 * History
 *   Jul 19, 2016 (winter): created
 */
package org.knime.base.node.stats.testing.wilcoxonsignedrank2;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTable;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.DataTableSpecCreator;
import org.knime.core.data.DoubleValue;
import org.knime.core.data.def.DefaultRow;
import org.knime.core.data.def.DoubleCell;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;

/**
 * @author Patrick Winter, University of Konstanz
 */
final class ColumnStatistics {

    private ColumnStatistics() {
    }

    /**
     * @param computeMedian True if the median column should be added
     * @return Specs for the column stats table
     */
    static DataTableSpec createSpec(final boolean computeMedian) {
        final DataTableSpecCreator tableSpecCreator = new DataTableSpecCreator();
        tableSpecCreator.addColumns(new DataColumnSpecCreator("Column", StringCell.TYPE).createSpec());
        tableSpecCreator.addColumns(new DataColumnSpecCreator("N", IntCell.TYPE).createSpec());
        tableSpecCreator.addColumns(new DataColumnSpecCreator("Missing Count", IntCell.TYPE).createSpec());
        tableSpecCreator.addColumns(new DataColumnSpecCreator("Mean", DoubleCell.TYPE).createSpec());
        tableSpecCreator.addColumns(new DataColumnSpecCreator("Standard Deviation", DoubleCell.TYPE).createSpec());
        tableSpecCreator.addColumns(new DataColumnSpecCreator("Standard Error Mean", DoubleCell.TYPE).createSpec());
        if (computeMedian) {
            tableSpecCreator.addColumns(new DataColumnSpecCreator("Median", DoubleCell.TYPE).createSpec());
        }
        return tableSpecCreator.createSpec();
    }

    /**
     * Creates a table containing statistics for the given columns.
     *
     * @param table Table containing the columns
     * @param columns The columns to calculate statistics from
     * @param exec Execution context used to create the new table
     * @param computeMedian True if the median should be computed
     * @return Table containing the stats for the given columns
     */
    static BufferedDataTable createTable(final DataTable table, final List<String> columns, final ExecutionContext exec,
        final boolean computeMedian) {
        final DataTableSpec spec = table.getDataTableSpec();
        final BufferedDataContainer container = exec.createDataContainer(createSpec(computeMedian));
        final List<ColumnStatistic> stats = new ArrayList<>(columns.size());
        for (final String column : columns) {
            stats.add(new ColumnStatistic(spec.findColumnIndex(column)));
        }
        for (final DataRow row : table) {
            for (final ColumnStatistic stat : stats) {
                final int index = stat.getIndex();
                final DataCell cell = row.getCell(index);
                if (cell.isMissing()) {
                    stat.setMissingValues(stat.getMissingValues() + 1);
                } else {
                    stat.getStatistics().addValue(((DoubleValue)cell).getDoubleValue());
                }
            }
        }
        int i = 0;
        for (final ColumnStatistic stat : stats) {
            container.addRowToTable(createRow(stat.getStatistics(), spec.getColumnSpec(stat.getIndex()).getName(),
                stat.getMissingValues(), "Row" + i++, computeMedian));
        }
        container.close();
        return container.getTable();
    }

    private static DataRow createRow(final DescriptiveStatistics stats, final String name, final int missingValues,
        final String id, final boolean computeMedian) {
        if (computeMedian) {
            return new DefaultRow(id, new StringCell(name), new IntCell((int)stats.getN()), new IntCell(missingValues),
                new DoubleCell(stats.getMean()), new DoubleCell(stats.getStandardDeviation()),
                new DoubleCell(calcStdError(stats)), new DoubleCell(stats.getPercentile(50)));
        }
        return new DefaultRow(id, new StringCell(name), new IntCell((int)stats.getN()), new IntCell(missingValues),
            new DoubleCell(stats.getMean()), new DoubleCell(stats.getStandardDeviation()),
            new DoubleCell(calcStdError(stats)));
    }

    private static double calcStdError(final DescriptiveStatistics stats) {
        return stats.getStandardDeviation() / Math.sqrt(stats.getN());
    }

    private static class ColumnStatistic {

        private final int index;

        private int missingValues;

        private final DescriptiveStatistics statistics;

        private ColumnStatistic(final int index1) {
            this.index = index1;
            this.missingValues = 0;
            this.statistics = new DescriptiveStatistics();
        }

        /**
         * @return the index
         */
        private int getIndex() {
            return index;
        }

        /**
         * @return the missingValues
         */
        private int getMissingValues() {
            return missingValues;
        }

        /**
         * @param missingValues1 the missingValues to set
         */
        private void setMissingValues(final int missingValues1) {
            this.missingValues = missingValues1;
        }

        /**
         * @return the statistics
         */
        private DescriptiveStatistics getStatistics() {
            return statistics;
        }
    }

}

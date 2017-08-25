/*
 * ------------------------------------------------------------------------
 *
 *  Copyright by KNIME GmbH, Konstanz, Germany
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
 *  KNIME and ECLIPSE being a combined program, KNIME GMBH herewith grants
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
 *   5 Jul 2017 (albrecht): created
 */
package org.knime.base.node.stats.dataexplorer;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.knime.base.data.statistics.HistogramModel;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;
import org.knime.js.core.JSONDataTable;
import org.knime.js.core.JSONDataTable.JSONDataTableRow;
import org.knime.js.core.JSONDataTableSpec;
import org.knime.js.core.JSONViewContent;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 *
 * @author Christian Albrecht, KNIME GmbH, Konstanz, Germany
 */
@JsonAutoDetect
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class DataExplorerNodeRepresentation extends JSONViewContent {

    private static final String CFG_STATISTICS = "statistics";
    private JSONDataTable m_statistics;

    private boolean m_enablePaging;
    private int m_initialPageSize;
    private boolean m_enablePageSizeChange;
    private int[] m_allowedPageSizes;
    private boolean m_pageSizeShowAll;
    private boolean m_enableJumpToPage;
    private boolean m_displayRowIds;
    private boolean m_displayColumnHeaders;
    private boolean m_displayFullscreenButton;
    private boolean m_fixedHeaders;
    private String m_title;
    private String m_subtitle;
    private boolean m_enableSelection;
    private boolean m_enableSearching;
    private boolean m_enableSorting;
    private boolean m_enableClearSortButton;
    private boolean m_enableGlobalNumberFormat;
    private int m_globalNumberFormatDecimals;
    private boolean m_displayMissingValueAsQuestionMark;
    private List<HistogramModel<?>> m_histograms;

    private static final String CFG_PREVIEW = "preview";
    private JSONDataTable m_preview;

    /**
     * @return the statistics
     */
    public JSONDataTable getStatistics() {
        return m_statistics;
    }

    /**
     * @param statistics the statistics to set
     */
    public void setStatistics(final JSONDataTable statistics) {
        m_statistics = statistics;
    }

    /**
     * @return the enablePaging
     */
    public boolean getEnablePaging() {
        return m_enablePaging;
    }

    /**
     * @param enablePaging the enablePaging to set
     */
    public void setEnablePaging(final boolean enablePaging) {
        m_enablePaging = enablePaging;
    }

    /**
     * @return the initialPageSize
     */
    public int getInitialPageSize() {
        return m_initialPageSize;
    }

    /**
     * @param initialPageSize the initialPageSize to set
     */
    public void setInitialPageSize(final int initialPageSize) {
        m_initialPageSize = initialPageSize;
    }

    /**
     * @return the enablePageSizeChange
     */
    public boolean getEnablePageSizeChange() {
        return m_enablePageSizeChange;
    }

    /**
     * @param enablePageSizeChange the enablePageSizeChange to set
     */
    public void setEnablePageSizeChange(final boolean enablePageSizeChange) {
        m_enablePageSizeChange = enablePageSizeChange;
    }

    /**
     * @return the allowedPageSizes
     */
    public int[] getAllowedPageSizes() {
        return m_allowedPageSizes;
    }

    /**
     * @param allowedPageSizes the allowedPageSizes to set
     */
    public void setAllowedPageSizes(final int[] allowedPageSizes) {
        m_allowedPageSizes = allowedPageSizes;
    }

    /**
     * @return the pageSizeShowAll
     */
    public boolean getPageSizeShowAll() {
        return m_pageSizeShowAll;
    }

    /**
     * @param pageSizeShowAll the pageSizeShowAll to set
     */
    public void setPageSizeShowAll(final boolean pageSizeShowAll) {
        m_pageSizeShowAll = pageSizeShowAll;
    }

    /**
     * @return the enableJumpToPage
     */
    public boolean getEnableJumpToPage() {
        return m_enableJumpToPage;
    }

    /**
     * @param enableJumpToPage the enableJumpToPage to set
     */
    public void setEnableJumpToPage(final boolean enableJumpToPage) {
        m_enableJumpToPage = enableJumpToPage;
    }

    /**
     * @return the displayRowIds
     */
    public boolean getDisplayRowIds() {
        return m_displayRowIds;
    }

    /**
     * @param displayRowIds the displayRowIds to set
     */
    public void setDisplayRowIds(final boolean displayRowIds) {
        m_displayRowIds = displayRowIds;
    }

    /**
     * @return the displayColumnHeaders
     */
    public boolean getDisplayColumnHeaders() {
        return m_displayColumnHeaders;
    }

    /**
     * @param displayColumnHeaders the displayColumnHeaders to set
     */
    public void setDisplayColumnHeaders(final boolean displayColumnHeaders) {
        m_displayColumnHeaders = displayColumnHeaders;
    }

    /**
     * @return the displayFullscreenButton
     */
    public boolean getDisplayFullscreenButton() {
        return m_displayFullscreenButton;
    }

    /**
     * @param displayFullscreenButton the displayFullscreenButton to set
     */
    public void setDisplayFullscreenButton(final boolean displayFullscreenButton) {
        m_displayFullscreenButton = displayFullscreenButton;
    }

    /**
     * @return the fixedHeaders
     */
    public boolean getFixedHeaders() {
        return m_fixedHeaders;
    }

    /**
     * @param fixedHeaders the fixedHeaders to set
     */
    public void setFixedHeaders(final boolean fixedHeaders) {
        m_fixedHeaders = fixedHeaders;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return m_title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(final String title) {
        m_title = title;
    }

    /**
     * @return the subtitle
     */
    public String getSubtitle() {
        return m_subtitle;
    }

    /**
     * @param subtitle the subtitle to set
     */
    public void setSubtitle(final String subtitle) {
        m_subtitle = subtitle;
    }

    /**
     * @return the enableSelection
     */
    public boolean getEnableSelection() {
        return m_enableSelection;
    }

    /**
     * @param enableSelection the enableSelection to set
     */
    public void setEnableSelection(final boolean enableSelection) {
        m_enableSelection = enableSelection;
    }

    /**
     * @return the enableSearching
     */
    public boolean getEnableSearching() {
        return m_enableSearching;
    }

    /**
     * @param enableSearching the enableSearching to set
     */
    public void setEnableSearching(final boolean enableSearching) {
        m_enableSearching = enableSearching;
    }

    /**
     * @return the enableSorting
     */
    public boolean getEnableSorting() {
        return m_enableSorting;
    }

    /**
     * @param enableSorting the enableSorting to set
     */
    public void setEnableSorting(final boolean enableSorting) {
        m_enableSorting = enableSorting;
    }

    /**
     * @return the enableClearSortButton
     */
    public boolean getEnableClearSortButton() {
        return m_enableClearSortButton;
    }

    /**
     * @param enableClearSortButton the enableClearSortButton to set
     */
    public void setEnableClearSortButton(final boolean enableClearSortButton) {
        m_enableClearSortButton = enableClearSortButton;
    }

    /**
     * @return the enableGlobalNumberFormat
     */
    public boolean getEnableGlobalNumberFormat() {
        return m_enableGlobalNumberFormat;
    }

    /**
     * @param enableGlobalNumberFormat the enableGlobalNumberFormat to set
     */
    public void setEnableGlobalNumberFormat(final boolean enableGlobalNumberFormat) {
        m_enableGlobalNumberFormat = enableGlobalNumberFormat;
    }

    /**
     * @return the globalNumberFormatDecimals
     */
    public int getGlobalNumberFormatDecimals() {
        return m_globalNumberFormatDecimals;
    }

    /**
     * @param globalNumberFormatDecimals the globalNumberFormatDecimals to set
     */
    public void setGlobalNumberFormatDecimals(final int globalNumberFormatDecimals) {
        m_globalNumberFormatDecimals = globalNumberFormatDecimals;
    }

    /**
     * @return the displayMissingValueAsQuestionMark
     */
    public boolean getDisplayMissingValueAsQuestionMark() {
        return m_displayMissingValueAsQuestionMark;
    }

    /**
     * @param displayMissingValueAsQuestionMark the displayMissingValueAsQuestionMark to set
     */
    public void setDisplayMissingValueAsQuestionMark(final boolean displayMissingValueAsQuestionMark) {
        m_displayMissingValueAsQuestionMark = displayMissingValueAsQuestionMark;
    }

    /**
     * @return the histograms
     */
    public List<HistogramModel<?>> getHistograms() {
        return m_histograms;
    }

    /**
     * @param histograms the histograms to set
     */
    public void setHistograms(final List<HistogramModel<?>> histograms) {
        m_histograms = histograms;
    }

    /**
     * @return the preview
     */
    public JSONDataTable getDataPreview() {
        return m_preview;
    }

    /**
     * @param preview the preview to set
     *
     */
    public void setDataPreview(final JSONDataTable preview) {
        m_preview = preview;
    }

    /**
     * Extracts all mean values from statistics table.
     * @return a double array with all mean values, may be null if operation not possible
     */
    @JsonIgnore
    double[] getMeans() {
        if (m_statistics != null) {
            JSONDataTableSpec spec = m_statistics.getSpec();
            List<String> colNames = Arrays.asList(spec.getColNames());
            if (!colNames.contains(DataExplorerConfig.MEAN)) {
                return null;
            }
            double[] means = new double[m_statistics.getSpec().getNumRows()];
            int meanIndex = colNames.indexOf(DataExplorerConfig.MEAN);
            JSONDataTableRow[] rows = m_statistics.getRows();
            for (int i = 0; i < rows.length; i++) {
                JSONDataTableRow row = rows[i];
                means[i] = (double)row.getData()[meanIndex];
            }
            return means;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @JsonIgnore
    public void saveToNodeSettings(final NodeSettingsWO settings) {
        NodeSettingsWO statSettings = settings.addNodeSettings(CFG_STATISTICS);
        m_statistics.saveJSONToNodeSettings(statSettings);

        settings.addBoolean(DataExplorerConfig.CFG_ENABLE_PAGING, m_enablePaging);
        settings.addInt(DataExplorerConfig.CFG_INITIAL_PAGE_SIZE, m_initialPageSize);
        settings.addBoolean(DataExplorerConfig.CFG_ENABLE_PAGE_SIZE_CHANGE, m_enablePageSizeChange);
        settings.addIntArray(DataExplorerConfig.CFG_PAGE_SIZES, m_allowedPageSizes);
        settings.addBoolean(DataExplorerConfig.CFG_PAGE_SIZE_SHOW_ALL, m_pageSizeShowAll);
        settings.addBoolean(DataExplorerConfig.CFG_ENABLE_JUMP_TO_PAGE, m_enableJumpToPage);
        settings.addBoolean(DataExplorerConfig.CFG_DISPLAY_ROW_IDS, m_displayRowIds);
        settings.addBoolean(DataExplorerConfig.CFG_DISPLAY_COLUMN_HEADERS, m_displayColumnHeaders);
        settings.addBoolean(DataExplorerConfig.CFG_FIXED_HEADERS, m_fixedHeaders);
        settings.addString(DataExplorerConfig.CFG_TITLE, m_title);
        settings.addString(DataExplorerConfig.CFG_SUBTITLE, m_subtitle);
        settings.addBoolean(DataExplorerConfig.CFG_ENABLE_SELECTION, m_enableSelection);
        settings.addBoolean(DataExplorerConfig.CFG_ENABLE_SEARCHING, m_enableSearching);
        settings.addBoolean(DataExplorerConfig.CFG_ENABLE_SORTING, m_enableSorting);
        settings.addBoolean(DataExplorerConfig.CFG_ENABLE_CLEAR_SORT_BUTTON, m_enableClearSortButton);
        settings.addBoolean(DataExplorerConfig.CFG_ENABLE_GLOBAL_NUMBER_FORMAT, m_enableGlobalNumberFormat);
        settings.addInt(DataExplorerConfig.CFG_GLOBAL_NUMBER_FORMAT_DECIMALS, m_globalNumberFormatDecimals);
        settings.addBoolean(DataExplorerConfig.CFG_DISPLAY_FULLSCREEN_BUTTON, m_displayFullscreenButton);
        settings.addBoolean(DataExplorerConfig.CFG_DISPLAY_MISSING_VALUE_AS_QUESTION_MARK, m_displayMissingValueAsQuestionMark);

        NodeSettingsWO prevSettings = settings.addNodeSettings(CFG_PREVIEW);
        m_preview.saveJSONToNodeSettings(prevSettings);
        // histograms are saved as extra file in DataExplorerNodeModel#saveInternals()
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @JsonIgnore
    public void loadFromNodeSettings(final NodeSettingsRO settings) throws InvalidSettingsException {
        NodeSettingsRO statSettings = settings.getNodeSettings(CFG_STATISTICS);
        m_statistics = JSONDataTable.loadFromNodeSettings(statSettings);

        m_enablePaging = settings.getBoolean(DataExplorerConfig.CFG_ENABLE_PAGING);
        m_initialPageSize = settings.getInt(DataExplorerConfig.CFG_INITIAL_PAGE_SIZE);
        m_enablePageSizeChange = settings.getBoolean(DataExplorerConfig.CFG_ENABLE_PAGE_SIZE_CHANGE);
        m_allowedPageSizes = settings.getIntArray(DataExplorerConfig.CFG_PAGE_SIZES);
        m_pageSizeShowAll = settings.getBoolean(DataExplorerConfig.CFG_PAGE_SIZE_SHOW_ALL);
        m_enableJumpToPage = settings.getBoolean(DataExplorerConfig.CFG_ENABLE_JUMP_TO_PAGE);
        m_displayRowIds = settings.getBoolean(DataExplorerConfig.CFG_DISPLAY_ROW_IDS);
        m_displayColumnHeaders = settings.getBoolean(DataExplorerConfig.CFG_DISPLAY_COLUMN_HEADERS);
        m_fixedHeaders = settings.getBoolean(DataExplorerConfig.CFG_FIXED_HEADERS);
        m_title = settings.getString(DataExplorerConfig.CFG_TITLE);
        m_subtitle = settings.getString(DataExplorerConfig.CFG_SUBTITLE);
        m_enableSelection = settings.getBoolean(DataExplorerConfig.CFG_ENABLE_SELECTION);
        m_enableSearching = settings.getBoolean(DataExplorerConfig.CFG_ENABLE_SEARCHING);
        m_enableSorting = settings.getBoolean(DataExplorerConfig.CFG_ENABLE_SORTING);
        m_enableClearSortButton = settings.getBoolean(DataExplorerConfig.CFG_ENABLE_CLEAR_SORT_BUTTON);
        m_enableGlobalNumberFormat = settings.getBoolean(DataExplorerConfig.CFG_ENABLE_GLOBAL_NUMBER_FORMAT);
        m_globalNumberFormatDecimals = settings.getInt(DataExplorerConfig.CFG_GLOBAL_NUMBER_FORMAT_DECIMALS);
        m_displayFullscreenButton = settings.getBoolean(DataExplorerConfig.CFG_DISPLAY_FULLSCREEN_BUTTON, DataExplorerConfig.DEFAULT_DISPLAY_FULLSCREEN_BUTTON);
        m_displayMissingValueAsQuestionMark = settings.getBoolean(DataExplorerConfig.CFG_DISPLAY_MISSING_VALUE_AS_QUESTION_MARK, DataExplorerConfig.DEFAULT_DISPLAY_MISSING_VALUE_AS_QUESTION_MARK);

        NodeSettingsRO prevSettings = settings.getNodeSettings(CFG_PREVIEW);
        m_preview = JSONDataTable.loadFromNodeSettings(prevSettings);
        // histograms are loaded separately in DataExplorerNodeModel#loadInternals()
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @JsonIgnore
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        DataExplorerNodeRepresentation other = (DataExplorerNodeRepresentation)obj;
        return new EqualsBuilder()
                .append(m_statistics, other.m_statistics)
                .append(m_enablePaging, other.m_enablePaging)
                .append(m_initialPageSize, other.m_initialPageSize)
                .append(m_enablePageSizeChange, other.m_enablePageSizeChange)
                .append(m_allowedPageSizes, other.m_allowedPageSizes)
                .append(m_pageSizeShowAll, other.m_pageSizeShowAll)
                .append(m_enableJumpToPage, other.m_enableJumpToPage)
                .append(m_displayRowIds, other.m_displayRowIds)
                .append(m_displayColumnHeaders, other.m_displayColumnHeaders)
                .append(m_displayFullscreenButton, other.m_displayFullscreenButton)
                .append(m_fixedHeaders, other.m_fixedHeaders)
                .append(m_title, other.m_title)
                .append(m_subtitle, other.m_subtitle)
                .append(m_enableSelection, other.m_enableSelection)
                .append(m_enableSearching, other.m_enableSearching)
                .append(m_enableSorting, other.m_enableSorting)
                .append(m_enableClearSortButton, other.m_enableClearSortButton)
                .append(m_enableGlobalNumberFormat, other.m_enableGlobalNumberFormat)
                .append(m_globalNumberFormatDecimals, other.m_globalNumberFormatDecimals)
                .append(m_displayMissingValueAsQuestionMark, other.m_displayMissingValueAsQuestionMark)
                .append(m_histograms, other.m_histograms)
                .append(m_preview, other.m_preview)
                .isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @JsonIgnore
    public int hashCode() {
        return new HashCodeBuilder()
                .append(m_statistics)
                .append(m_enablePaging)
                .append(m_initialPageSize)
                .append(m_enablePageSizeChange)
                .append(m_allowedPageSizes)
                .append(m_pageSizeShowAll)
                .append(m_enableJumpToPage)
                .append(m_displayRowIds)
                .append(m_displayColumnHeaders)
                .append(m_displayFullscreenButton)
                .append(m_fixedHeaders)
                .append(m_title)
                .append(m_subtitle)
                .append(m_enableSelection)
                .append(m_enableSearching)
                .append(m_enableSorting)
                .append(m_enableClearSortButton)
                .append(m_enableGlobalNumberFormat)
                .append(m_globalNumberFormatDecimals)
                .append(m_displayMissingValueAsQuestionMark)
                .append(m_histograms)
                .append(m_preview)
                .toHashCode();
    }


}
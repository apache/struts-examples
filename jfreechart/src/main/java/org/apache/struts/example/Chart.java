/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.struts.example;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.lang3.RandomUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Chart extends ActionSupport {

    private JFreeChart chart;

    public String execute() throws Exception {
        XYSeries dataSeries = new XYSeries(1);
        for (int i = 0; i <= 100; i++) {
            dataSeries.add(i, RandomUtils.nextInt());
        }
        XYSeriesCollection xyDataset = new XYSeriesCollection(dataSeries);

        ValueAxis xAxis = new NumberAxis("Raw Marks");
        ValueAxis yAxis = new NumberAxis("Moderated Marks");

        chart = new JFreeChart(
                        "Moderation Function",
                        JFreeChart.DEFAULT_TITLE_FONT,
                        new XYPlot(
                                xyDataset,
                                xAxis,
                                yAxis,
                                new StandardXYItemRenderer(StandardXYItemRenderer.LINES)),
                        false
        );

        chart.setBackgroundPaint(java.awt.Color.white);

        return SUCCESS;
    }

    public JFreeChart getChart() {
        return chart;
    }
}

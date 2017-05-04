/*
 * Sonar ESQL Plugin
 * Copyright (C) 2013-2017 Thomas Pohl and EXXETA AG
 * http://www.exxeta.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.exxeta.iss.sonar.esql.metrics;

import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.Metric;

public class EsqlMetrics {
	public static final String PROCEDURES_KEY = "procedures";
	public static final Metric<Integer> PROCEDURES = new Metric.Builder(PROCEDURES_KEY, "Procedures",
			Metric.ValueType.INT).setDescription("Procedures").setDirection(Metric.DIRECTION_WORST)
					.setQualitative(false).setDomain(CoreMetrics.DOMAIN_SIZE).create();
	public static final String MODULES_KEY = "modules";
	public static final Metric<Integer> MODULES = new Metric.Builder(MODULES_KEY, "Modules", Metric.ValueType.INT)
			.setDescription("Modules").setDirection(Metric.DIRECTION_WORST).setQualitative(false)
			.setDomain(CoreMetrics.DOMAIN_SIZE).create();
}
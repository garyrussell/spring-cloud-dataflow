/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.dataflow.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Enumeration of artifact types.
 *
 * @author Patrick Peralta
 * @author Eric Bottard
 */
public enum ArtifactType {
	/**
	 * A module type that appears in a stream, at first position.
	 */
	source,

	/**
	 * A module type that appears in a stream, in middle position.
	 */
	processor,

	/**
	 * A module type that appears in a stream, in last position.
	 */
	sink,

	/**
	 * A module type to execute a short-lived process.
	 */
	task,

	/**
	 * A supporting library to a module, either as a single (jar) maven artifact, or as a bom.
	 */
	library;

	public static final Collection<ArtifactType> STREAM_TYPES = Arrays.asList(source, processor, sink);
	public static final Collection<ArtifactType> TASK_TYPES = Arrays.asList(task);
	public static final Collection<ArtifactType> LIBRARY_TYPES = Arrays.asList(library);
	public static final Collection<ArtifactType> MODULE_TYPES = new ArrayList<>();
	static {
		MODULE_TYPES.addAll(STREAM_TYPES);
		MODULE_TYPES.addAll(TASK_TYPES);
	}
}

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

package org.springframework.cloud.dataflow.admin.spi.local;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.dataflow.module.deployer.ModuleDeployer;
import org.springframework.cloud.stream.module.launcher.ModuleLauncher;
import org.springframework.cloud.stream.module.launcher.ModuleLauncherConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Creates deployers that will deploy modules in-process.
 *
 * @author Eric Bottard
 */
@Configuration
public class LocalConfiguration {

	static final String LOCAL_DEPLOYER_PREFIX = "deployer.local";

	@ConditionalOnProperty(prefix = LOCAL_DEPLOYER_PREFIX, name = "out-of-process", havingValue = "false")
	@Import(ModuleLauncherConfiguration.class)
	public static class InProcess {

		@Bean
		public ModuleDeployer processModuleDeployer(ModuleLauncher moduleLauncher) {
			return new InProcessModuleDeployer(moduleLauncher);
		}

		@Bean
		public ModuleDeployer taskModuleDeployer(ModuleLauncher moduleLauncher) {
			return processModuleDeployer(moduleLauncher);
		}
	}

	@EnableConfigurationProperties(OutOfProcessModuleDeployerProperties.class)
	@ConditionalOnProperty(prefix = LOCAL_DEPLOYER_PREFIX, name = "out-of-process", havingValue = "true", matchIfMissing = true)
	public static class OutOfProcess {

		@Bean
		public ModuleDeployer processModuleDeployer() throws Exception {
			return new OutOfProcessModuleDeployer();
		}

		@Bean
		public ModuleDeployer taskModuleDeployer() throws Exception {
			return processModuleDeployer();
		}

	}

}

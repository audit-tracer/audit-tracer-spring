<div id="top">

<!-- HEADER STYLE: CLASSIC -->
<div align="center">

# AUDIT-TRACER-SPRING

<em>Seamless Visibility, Unmatched Accountability, Powered by Innovation</em>

<!-- BADGES -->
<img src="https://img.shields.io/github/license/audit-tracer/audit-tracer-spring?style=flat&logo=opensourceinitiative&logoColor=white&color=0080ff" alt="license">
<img src="https://img.shields.io/github/last-commit/audit-tracer/audit-tracer-spring?style=flat&logo=git&logoColor=white&color=0080ff" alt="last-commit">
<img src="https://img.shields.io/github/languages/top/audit-tracer/audit-tracer-spring?style=flat&color=0080ff" alt="repo-top-language">
<img src="https://img.shields.io/github/languages/count/audit-tracer/audit-tracer-spring?style=flat&color=0080ff" alt="repo-language-count">

<em>Built with the tools and technologies:</em>

<img src="https://img.shields.io/badge/JSON-000000.svg?style=flat&logo=JSON&logoColor=white" alt="JSON">
<img src="https://img.shields.io/badge/Markdown-000000.svg?style=flat&logo=Markdown&logoColor=white" alt="Markdown">
<img src="https://img.shields.io/badge/Spring-000000.svg?style=flat&logo=Spring&logoColor=white" alt="Spring">
<img src="https://img.shields.io/badge/XML-005FAD.svg?style=flat&logo=XML&logoColor=white" alt="XML">

</div>
<br>

---

## ✨ Overview

audit-tracer-spring is a comprehensive library designed to embed audit logging capabilities into Spring-based applications. It enables detailed monitoring, logging, and traceability of operational activities, ensuring transparency and accountability across distributed systems. The tool simplifies integration through streamlined configuration and supports scalable, reliable audit trail management.

**Why audit-tracer-spring?**

This project helps developers implement robust audit trails with minimal effort. The core features include:

- 🛠️ **Spring Integration:** Seamlessly incorporates audit tracing into your existing Spring Boot applications.
- ⚡ **Batch & Async Processing:** Ensures reliable, high-performance transmission of audit logs with batching and asynchronous operations.
- 🔍 **Custom Annotations & Interceptors:** Facilitates detailed tracking of user actions and system events with minimal boilerplate.
- 🔧 **Configurable & Flexible:** Easily tailored to different environments with comprehensive property-based setup.
- 🔒 **Resilient & Robust:** Built-in error handling and retry mechanisms for dependable audit logging.

---

### 📑 Project Index

<details open>
	<summary><b><code>AUDIT-TRACER-SPRING/</code></b></summary>
	<!-- __root__ Submodule -->
	<details>
		<summary><b>__root__</b></summary>
		<blockquote>
			<div class='directory-path' style='padding: 8px 0; color: #666;'>
				<code><b>⦿ __root__</b></code>
			<table style='width: 100%; border-collapse: collapse;'>
			<thead>
				<tr style='background-color: #f8f9fa;'>
					<th style='width: 30%; text-align: left; padding: 8px;'>File Name</th>
					<th style='text-align: left; padding: 8px;'>Summary</th>
				</tr>
			</thead>
				<tr style='border-bottom: 1px solid #eee;'>
					<td style='padding: 8px;'><b><a href='https://github.com/audit-tracer/audit-tracer-spring/blob/master/README.md'>README.md</a></b></td>
					<td style='padding: 8px;'>- Provides integration capabilities for audit tracing within Spring-based applications, enabling seamless monitoring and logging of operational activities<br>- Facilitates the incorporation of audit trail functionalities into the overall architecture, ensuring comprehensive visibility and accountability across distributed systems<br>- Supports streamlined setup through Maven repository configuration, enhancing ease of adoption and consistent implementation.</td>
				</tr>
				<tr style='border-bottom: 1px solid #eee;'>
					<td style='padding: 8px;'><b><a href='https://github.com/audit-tracer/audit-tracer-spring/blob/master/LICENSE'>LICENSE</a></b></td>
					<td style='padding: 8px;'>- Provides the licensing terms for the project, establishing legal permissions and restrictions for software use, distribution, and modification within the overall architecture<br>- Ensures clarity on rights granted to users and contributors, supporting open-source collaboration and legal compliance across the codebase.</td>
				</tr>
				<tr style='border-bottom: 1px solid #eee;'>
					<td style='padding: 8px;'><b><a href='https://github.com/audit-tracer/audit-tracer-spring/blob/master/pom.xml'>pom.xml</a></b></td>
					<td style='padding: 8px;'>- Defines the projects Maven configuration for a Spring Boot-based audit tracing application, managing dependencies, build settings, and versioning<br>- Facilitates integration of core frameworks like Spring Boot, AOP, security, and retry mechanisms, enabling robust, scalable, and secure audit logging functionalities within the overall architecture.</td>
				</tr>
			</table>
		</blockquote>
	</details>
	<!-- src Submodule -->
	<details>
		<summary><b>src</b></summary>
		<blockquote>
			<div class='directory-path' style='padding: 8px 0; color: #666;'>
				<code><b>⦿ src</b></code>
			<!-- main Submodule -->
			<details>
				<summary><b>main</b></summary>
				<blockquote>
					<div class='directory-path' style='padding: 8px 0; color: #666;'>
						<code><b>⦿ src.main</b></code>
					<!-- resources Submodule -->
					<details>
						<summary><b>resources</b></summary>
						<blockquote>
							<div class='directory-path' style='padding: 8px 0; color: #666;'>
								<code><b>⦿ src.main.resources</b></code>
							<!-- META-INF Submodule -->
							<details>
								<summary><b>META-INF</b></summary>
								<blockquote>
									<div class='directory-path' style='padding: 8px 0; color: #666;'>
										<code><b>⦿ src.main.resources.META-INF</b></code>
									<table style='width: 100%; border-collapse: collapse;'>
									<thead>
										<tr style='background-color: #f8f9fa;'>
											<th style='width: 30%; text-align: left; padding: 8px;'>File Name</th>
											<th style='text-align: left; padding: 8px;'>Summary</th>
										</tr>
									</thead>
										<tr style='border-bottom: 1px solid #eee;'>
											<td style='padding: 8px;'><b><a href='https://github.com/audit-tracer/audit-tracer-spring/blob/master/src/main/resources/META-INF/spring-configuration-metadata.json'>spring-configuration-metadata.json</a></b></td>
											<td style='padding: 8px;'>- Defines configuration properties for the audit-tracer module, enabling seamless integration with the audit server<br>- It manages essential settings such as server URL, API key, environment, and thread pool configurations, facilitating reliable and customizable audit logging within the overall application architecture<br>- This setup ensures flexible, secure, and efficient tracing of application activities across different environments.</td>
										</tr>
									</table>
								</blockquote>
							</details>
						</blockquote>
					</details>
					<!-- java Submodule -->
					<details>
						<summary><b>java</b></summary>
						<blockquote>
							<div class='directory-path' style='padding: 8px 0; color: #666;'>
								<code><b>⦿ src.main.java</b></code>
							<!-- com Submodule -->
							<details>
								<summary><b>com</b></summary>
								<blockquote>
									<div class='directory-path' style='padding: 8px 0; color: #666;'>
										<code><b>⦿ src.main.java.com</b></code>
									<!-- audittracer Submodule -->
									<details>
										<summary><b>audittracer</b></summary>
										<blockquote>
											<div class='directory-path' style='padding: 8px 0; color: #666;'>
												<code><b>⦿ src.main.java.com.audittracer</b></code>
											<!-- client Submodule -->
											<details>
												<summary><b>client</b></summary>
												<blockquote>
													<div class='directory-path' style='padding: 8px 0; color: #666;'>
														<code><b>⦿ src.main.java.com.audittracer.client</b></code>
													<!-- spring Submodule -->
													<details>
														<summary><b>spring</b></summary>
														<blockquote>
															<div class='directory-path' style='padding: 8px 0; color: #666;'>
																<code><b>⦿ src.main.java.com.audittracer.client.spring</b></code>
															<table style='width: 100%; border-collapse: collapse;'>
															<thead>
																<tr style='background-color: #f8f9fa;'>
																	<th style='width: 30%; text-align: left; padding: 8px;'>File Name</th>
																	<th style='text-align: left; padding: 8px;'>Summary</th>
																</tr>
															</thead>
																<tr style='border-bottom: 1px solid #eee;'>
																	<td style='padding: 8px;'><b><a href='https://github.com/audit-tracer/audit-tracer-spring/blob/master/src/main/java/com/audittracer/client/spring/AuditTracerName.java'>AuditTracerName.java</a></b></td>
																	<td style='padding: 8px;'>- Defines constant identifiers and configuration parameters for the AuditTracer client within the Spring framework, facilitating consistent referencing of service names, API endpoints, headers, and logging formats across the codebase<br>- Serves as a centralized reference point to ensure uniformity and ease of maintenance in integrating audit tracing functionalities throughout the application architecture.</td>
																</tr>
																<tr style='border-bottom: 1px solid #eee;'>
																	<td style='padding: 8px;'><b><a href='https://github.com/audit-tracer/audit-tracer-spring/blob/master/src/main/java/com/audittracer/client/spring/ActionService.java'>ActionService.java</a></b></td>
																	<td style='padding: 8px;'>- Facilitates batching and asynchronous transmission of audit actions to an external API, ensuring reliable logging and monitoring within the applications architecture<br>- Manages action queuing, batch processing, retries, and error handling, while integrating with Springs lifecycle for connection validation and graceful shutdown, thereby supporting scalable and resilient audit trail management across the system.</td>
																</tr>
																<tr style='border-bottom: 1px solid #eee;'>
																	<td style='padding: 8px;'><b><a href='https://github.com/audit-tracer/audit-tracer-spring/blob/master/src/main/java/com/audittracer/client/spring/Action.java'>Action.java</a></b></td>
																	<td style='padding: 8px;'>- Defines an immutable Action entity representing audit events within the system, encapsulating details such as action type, target information, metadata, and timestamp<br>- Serves as a core component for capturing and uniquely identifying audit logs, facilitating traceability and compliance across the applications auditing architecture.</td>
																</tr>
																<tr style='border-bottom: 1px solid #eee;'>
																	<td style='padding: 8px;'><b><a href='https://github.com/audit-tracer/audit-tracer-spring/blob/master/src/main/java/com/audittracer/client/spring/AuditType.java'>AuditType.java</a></b></td>
																	<td style='padding: 8px;'>- Defines the types of audits supported within the system, specifically distinguishing between application-level and database-level auditing<br>- Serves as a foundational component for categorizing audit events, enabling consistent handling and processing across the overall architecture<br>- Facilitates clear identification and management of different audit scopes within the auditing framework.</td>
																</tr>
																<tr style='border-bottom: 1px solid #eee;'>
																	<td style='padding: 8px;'><b><a href='https://github.com/audit-tracer/audit-tracer-spring/blob/master/src/main/java/com/audittracer/client/spring/AuditInterceptor.java'>AuditInterceptor.java</a></b></td>
																	<td style='padding: 8px;'>- Implements an aspect-oriented interceptor that captures method execution details annotated with Audit, evaluating dynamic expressions and contextual data such as security and HTTP request info<br>- It constructs comprehensive audit actions, enabling detailed tracking and logging of user activities and system events within the applications architecture.</td>
																</tr>
																<tr style='border-bottom: 1px solid #eee;'>
																	<td style='padding: 8px;'><b><a href='https://github.com/audit-tracer/audit-tracer-spring/blob/master/src/main/java/com/audittracer/client/spring/CachedMethodInfo.java'>CachedMethodInfo.java</a></b></td>
																	<td style='padding: 8px;'>- Encapsulates metadata related to audited methods within the Spring-based application, including annotations, parameters, and method names<br>- Facilitates efficient retrieval and management of method-specific audit information, supporting the overall architectures goal of comprehensive and organized audit logging for enhanced traceability and compliance.</td>
																</tr>
															</table>
															<!-- exception Submodule -->
															<details>
																<summary><b>exception</b></summary>
																<blockquote>
																	<div class='directory-path' style='padding: 8px 0; color: #666;'>
																		<code><b>⦿ src.main.java.com.audittracer.client.spring.exception</b></code>
																	<table style='width: 100%; border-collapse: collapse;'>
																	<thead>
																		<tr style='background-color: #f8f9fa;'>
																			<th style='width: 30%; text-align: left; padding: 8px;'>File Name</th>
																			<th style='text-align: left; padding: 8px;'>Summary</th>
																		</tr>
																	</thead>
																		<tr style='border-bottom: 1px solid #eee;'>
																			<td style='padding: 8px;'><b><a href='https://github.com/audit-tracer/audit-tracer-spring/blob/master/src/main/java/com/audittracer/client/spring/exception/RetryableException.java'>RetryableException.java</a></b></td>
																			<td style='padding: 8px;'>- Defines a custom exception to indicate transient failures that can be retried within the applications Spring client architecture<br>- It facilitates error handling by signaling when operations are safe to attempt again, thereby enhancing robustness and resilience in communication with external services or components<br>- This exception integrates into the broader error management strategy of the codebase.</td>
																		</tr>
																		<tr style='border-bottom: 1px solid #eee;'>
																			<td style='padding: 8px;'><b><a href='https://github.com/audit-tracer/audit-tracer-spring/blob/master/src/main/java/com/audittracer/client/spring/exception/FieldEmptyException.java'>FieldEmptyException.java</a></b></td>
																			<td style='padding: 8px;'>- Defines a custom runtime exception to signal cases where a required field is left empty during data validation or processing within the Spring client module<br>- Facilitates clear error handling and improves robustness by providing specific exception handling for missing input fields in the overall application architecture.</td>
																		</tr>
																		<tr style='border-bottom: 1px solid #eee;'>
																			<td style='padding: 8px;'><b><a href='https://github.com/audit-tracer/audit-tracer-spring/blob/master/src/main/java/com/audittracer/client/spring/exception/AuditException.java'>AuditException.java</a></b></td>
																			<td style='padding: 8px;'>- Defines a custom runtime exception to handle audit-related errors within the Spring client module<br>- Facilitates consistent error reporting and management across the audit tracing system, ensuring that exceptions specific to audit operations are distinguishable and easily handled within the overall application architecture.</td>
																		</tr>
																	</table>
																</blockquote>
															</details>
															<!-- dto Submodule -->
															<details>
																<summary><b>dto</b></summary>
																<blockquote>
																	<div class='directory-path' style='padding: 8px 0; color: #666;'>
																		<code><b>⦿ src.main.java.com.audittracer.client.spring.dto</b></code>
																	<table style='width: 100%; border-collapse: collapse;'>
																	<thead>
																		<tr style='background-color: #f8f9fa;'>
																			<th style='width: 30%; text-align: left; padding: 8px;'>File Name</th>
																			<th style='text-align: left; padding: 8px;'>Summary</th>
																		</tr>
																	</thead>
																		<tr style='border-bottom: 1px solid #eee;'>
																			<td style='padding: 8px;'><b><a href='https://github.com/audit-tracer/audit-tracer-spring/blob/master/src/main/java/com/audittracer/client/spring/dto/AuditBatchRequest.java'>AuditBatchRequest.java</a></b></td>
																			<td style='padding: 8px;'>- Defines a data transfer object for batching audit actions, encapsulating a list of actions, a unique batch identifier, and a timestamp<br>- Serves as a key component in the audit tracing architecture, enabling efficient transmission and processing of grouped audit events within the systems broader logging and compliance framework.</td>
																		</tr>
																	</table>
																</blockquote>
															</details>
															<!-- annotation Submodule -->
															<details>
																<summary><b>annotation</b></summary>
																<blockquote>
																	<div class='directory-path' style='padding: 8px 0; color: #666;'>
																		<code><b>⦿ src.main.java.com.audittracer.client.spring.annotation</b></code>
																	<table style='width: 100%; border-collapse: collapse;'>
																	<thead>
																		<tr style='background-color: #f8f9fa;'>
																			<th style='width: 30%; text-align: left; padding: 8px;'>File Name</th>
																			<th style='text-align: left; padding: 8px;'>Summary</th>
																		</tr>
																	</thead>
																		<tr style='border-bottom: 1px solid #eee;'>
																			<td style='padding: 8px;'><b><a href='https://github.com/audit-tracer/audit-tracer-spring/blob/master/src/main/java/com/audittracer/client/spring/annotation/AuditParam.java'>AuditParam.java</a></b></td>
																			<td style='padding: 8px;'>- Defines a custom annotation for marking method parameters involved in audit logging within the Spring framework<br>- Facilitates capturing specific parameter values during method execution to enhance traceability and accountability across the applications architecture<br>- Integrates seamlessly into the broader audit tracing system, supporting comprehensive monitoring and compliance efforts.</td>
																		</tr>
																		<tr style='border-bottom: 1px solid #eee;'>
																			<td style='padding: 8px;'><b><a href='https://github.com/audit-tracer/audit-tracer-spring/blob/master/src/main/java/com/audittracer/client/spring/annotation/EnableAuditTracer.java'>EnableAuditTracer.java</a></b></td>
																			<td style='padding: 8px;'>- Facilitates the activation of audit tracing capabilities within a Spring application by enabling essential configuration properties and component scanning<br>- It integrates audit tracing functionalities into the applications architecture, ensuring proper setup of thread management, queue handling, and property configurations for comprehensive audit logging and monitoring.</td>
																		</tr>
																		<tr style='border-bottom: 1px solid #eee;'>
																			<td style='padding: 8px;'><b><a href='https://github.com/audit-tracer/audit-tracer-spring/blob/master/src/main/java/com/audittracer/client/spring/annotation/Audit.java'>Audit.java</a></b></td>
																			<td style='padding: 8px;'>- Defines a custom annotation for marking methods with audit-related metadata, enabling automatic tracking of user actions and system events within the application<br>- It facilitates consistent and structured audit logging by specifying action details, target entities, and optional metadata, thereby supporting comprehensive compliance and monitoring across the overall system architecture.</td>
																		</tr>
																	</table>
																</blockquote>
															</details>
															<!-- config Submodule -->
															<details>
																<summary><b>config</b></summary>
																<blockquote>
																	<div class='directory-path' style='padding: 8px 0; color: #666;'>
																		<code><b>⦿ src.main.java.com.audittracer.client.spring.config</b></code>
																	<table style='width: 100%; border-collapse: collapse;'>
																	<thead>
																		<tr style='background-color: #f8f9fa;'>
																			<th style='width: 30%; text-align: left; padding: 8px;'>File Name</th>
																			<th style='text-align: left; padding: 8px;'>Summary</th>
																		</tr>
																	</thead>
																		<tr style='border-bottom: 1px solid #eee;'>
																			<td style='padding: 8px;'><b><a href='https://github.com/audit-tracer/audit-tracer-spring/blob/master/src/main/java/com/audittracer/client/spring/config/ApplicationConfiguration.java'>ApplicationConfiguration.java</a></b></td>
																			<td style='padding: 8px;'>- Configures core application components such as REST client, expression parser, and thread pools to support flexible, scalable execution within the audit tracer system<br>- It ensures proper setup of threading strategies, expression evaluation, and HTTP communication, facilitating efficient and adaptable operation aligned with project architecture.</td>
																		</tr>
																	</table>
																	<!-- properties Submodule -->
																	<details>
																		<summary><b>properties</b></summary>
																		<blockquote>
																			<div class='directory-path' style='padding: 8px 0; color: #666;'>
																				<code><b>⦿ src.main.java.com.audittracer.client.spring.config.properties</b></code>
																			<table style='width: 100%; border-collapse: collapse;'>
																			<thead>
																				<tr style='background-color: #f8f9fa;'>
																					<th style='width: 30%; text-align: left; padding: 8px;'>File Name</th>
																					<th style='text-align: left; padding: 8px;'>Summary</th>
																				</tr>
																			</thead>
																				<tr style='border-bottom: 1px solid #eee;'>
																					<td style='padding: 8px;'><b><a href='https://github.com/audit-tracer/audit-tracer-spring/blob/master/src/main/java/com/audittracer/client/spring/config/properties/PropertiesConfig.java'>PropertiesConfig.java</a></b></td>
																					<td style='padding: 8px;'>- Defines application-wide configuration properties for the AuditTracer client, including environment settings, API credentials, endpoint URL, and threading/queue configurations<br>- Serves as a central point for managing and injecting configuration data into the system, ensuring consistent setup and enabling flexible customization of the tracing and monitoring functionalities within the overall architecture.</td>
																				</tr>
																				<tr style='border-bottom: 1px solid #eee;'>
																					<td style='padding: 8px;'><b><a href='https://github.com/audit-tracer/audit-tracer-spring/blob/master/src/main/java/com/audittracer/client/spring/config/properties/Environment.java'>Environment.java</a></b></td>
																					<td style='padding: 8px;'>- Defines environment modes for the application, distinguishing between LIVE and LOCAL settings<br>- Facilitates environment-specific configurations and behaviors within the overall architecture, ensuring the system adapts appropriately based on deployment context<br>- Supports consistent environment management across the codebase, enabling smooth transitions and reliable operation in different deployment scenarios.</td>
																				</tr>
																			</table>
																			<!-- queue Submodule -->
																			<details>
																				<summary><b>queue</b></summary>
																				<blockquote>
																					<div class='directory-path' style='padding: 8px 0; color: #666;'>
																						<code><b>⦿ src.main.java.com.audittracer.client.spring.config.properties.queue</b></code>
																					<table style='width: 100%; border-collapse: collapse;'>
																					<thead>
																						<tr style='background-color: #f8f9fa;'>
																							<th style='width: 30%; text-align: left; padding: 8px;'>File Name</th>
																							<th style='text-align: left; padding: 8px;'>Summary</th>
																						</tr>
																					</thead>
																						<tr style='border-bottom: 1px solid #eee;'>
																							<td style='padding: 8px;'><b><a href='https://github.com/audit-tracer/audit-tracer-spring/blob/master/src/main/java/com/audittracer/client/spring/config/properties/queue/QueuePropertiesConfig.java'>QueuePropertiesConfig.java</a></b></td>
																							<td style='padding: 8px;'>- Defines configurable properties for audit tracer queues, specifically managing queue size within the applications messaging architecture<br>- Facilitates flexible adjustment of queue capacity to optimize message handling and system performance, integrating seamlessly into the overall Spring-based configuration setup for reliable and scalable message processing.</td>
																						</tr>
																					</table>
																				</blockquote>
																			</details>
																			<!-- thread Submodule -->
																			<details>
																				<summary><b>thread</b></summary>
																				<blockquote>
																					<div class='directory-path' style='padding: 8px 0; color: #666;'>
																						<code><b>⦿ src.main.java.com.audittracer.client.spring.config.properties.thread</b></code>
																					<table style='width: 100%; border-collapse: collapse;'>
																					<thead>
																						<tr style='background-color: #f8f9fa;'>
																							<th style='width: 30%; text-align: left; padding: 8px;'>File Name</th>
																							<th style='text-align: left; padding: 8px;'>Summary</th>
																						</tr>
																					</thead>
																						<tr style='border-bottom: 1px solid #eee;'>
																							<td style='padding: 8px;'><b><a href='https://github.com/audit-tracer/audit-tracer-spring/blob/master/src/main/java/com/audittracer/client/spring/config/properties/thread/ThreadPropertiesConfig.java'>ThreadPropertiesConfig.java</a></b></td>
																							<td style='padding: 8px;'>- Defines configurable thread management properties within the applications architecture, enabling flexible control over threading behavior such as thread type and size<br>- Facilitates optimized concurrency handling in the overall system, ensuring efficient resource utilization and performance tuning aligned with project requirements.</td>
																						</tr>
																						<tr style='border-bottom: 1px solid #eee;'>
																							<td style='padding: 8px;'><b><a href='https://github.com/audit-tracer/audit-tracer-spring/blob/master/src/main/java/com/audittracer/client/spring/config/properties/thread/ThreadType.java'>ThreadType.java</a></b></td>
																							<td style='padding: 8px;'>- Defines thread management strategies by categorizing thread types into FIXED and CACHE, facilitating configurable concurrency control within the applications architecture<br>- This enumeration supports flexible thread handling, ensuring optimized resource utilization and performance consistency across different operational contexts in the overall system.</td>
																						</tr>
																					</table>
																				</blockquote>
																			</details>
																		</blockquote>
																	</details>
																</blockquote>
															</details>
														</blockquote>
													</details>
												</blockquote>
											</details>
										</blockquote>
									</details>
								</blockquote>
							</details>
						</blockquote>
					</details>
				</blockquote>
			</details>
		</blockquote>
	</details>
</details>

---

## 🚀 Getting Started

- You can use it with this configuration in a pom file:

```xml
 <repositories>
    <repository>
      <id>repsy</id>
       <name>Audit Tracer Spring Lib Repsy Repository</name>
      <url>https://repo.repsy.io/mvn/audit-tracer/audit-tracer-spring-lib</url>
    </repository>
  </repositories>
```

## 📜 License

Audit-tracer-spring is protected under the [LICENSE](https://choosealicense.com/licenses) License. For more details, refer to the [LICENSE](https://choosealicense.com/licenses/) file.

---

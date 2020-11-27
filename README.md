#200921

For the ESS W20 course, all necessary settings will be provided by the project's configuration files and run configurations for IDEA.

Note that for running the project on Wildfly 20 and higher, EJB run configurations need to specify the following parameters for the Jackson JSON Processor:
+ -Dresteasy.preferJacksonOverJsonB=true 
+ -Dresteasy.jackson.deserialization.whitelist.allowIfBaseType.prefix=* 
+ -Dresteasy.jackson.deserialization.whitelist.allowIfSubType.prefix=*

Otherwise, usage of polymorphic types annotated with @JsonTypeInfo and foreseeing the full classname of concrete subtypes will result in a 400 error when accessing the web api.

Note further that when starting the EJB project together with JSF, the base url of the web api needs to changed in the client-side settings ess-ejb-client.properties
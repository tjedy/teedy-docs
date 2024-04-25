# User login
user: admin
pass: melody1405

# How to build
docker build -f Dockerfile -t secure-doc/teedy:2.0 .

Teedy is organized in several Maven modules:

- docs-core
- docs-web
- docs-web-common

First off, clone the repository: `git clone git://github.com/sismics/docs.git`
or download the sources from GitHub.

### Launch the build

From the root directory:

```console
mvn clean -DskipTests install
```

### Run a stand-alone version

From the `docs-web` directory:

```console
mvn jetty:run
```

### Build a .war to deploy to your servlet container for Docker

From the `docs-web` directory:

```console
mvn -Pprod -DskipTests clean install
```

You will get your deployable WAR in the `docs-web/target` directory.

### Run docs-besu

From the `docs-besu` directory:

```console
mvn clean spring-boot:run -DskipTest
```

# Call event
DocumentCreatedAsyncEvent documentCreatedAsyncEvent = new DocumentCreatedAsyncEvent();
documentCreatedAsyncEvent.setUserId("admin");
documentCreatedAsyncEvent.setDocumentId(document.getId());
ThreadLocalContext.get().addAsyncEvent(documentCreatedAsyncEvent);

# Set Alternative Java
update-alternatives --install "/usr/bin/java" "java" "/usr/local/openjdk-17/bin/java" 0
update-alternatives --install "/usr/bin/javac" "javac" "/usr/local/openjdk-17/bin/javac" 0
update-alternatives --set java /usr/local/openjdk-17/bin/java
update-alternatives --set javac /usr/local/openjdk-17/bin/javac
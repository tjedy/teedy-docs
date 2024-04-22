# User login
user: admin
pass: melody1405

# How to build
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
#
# This is based on a helpful blog posting about Docker + SBT + Scala
# https://medium.com/@yzhong.cs/getting-started-with-docker-scala-sbt-d91f8ac22f5f
#
# The following are changes:
# - make it work with whatever is the current version of SBT (or version desired)
# - make it work with SBT mirrors; Docker containers cannot seem to resolve
#   recursive queries (in alpine)
# - run tests
#

FROM openjdk:8

ENV SBT_V 0.13.17
ENV SBT_M sbt-downloads.cdnedge.bluemix.net

RUN \
  mkdir -p /sandbox && \
  curl -L -o sbt.tgz https://${SBT_M}/releases/v${SBT_V}/sbt-${SBT_V}.tgz && \
  tar xzf sbt.tgz -C /opt && \
  /opt/sbt/bin/sbt sbtVersion

WORKDIR /sandbox

ADD . /sandbox

CMD /opt/sbt/bin/sbt test


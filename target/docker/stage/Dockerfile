FROM openjdk:11.0.7 as stage0
LABEL snp-multi-stage="intermediate"
LABEL snp-multi-stage-id="b3d6d843-a121-49bc-a295-c028e9195210"
WORKDIR /opt/docker
COPY opt /opt
USER root
RUN ["chmod", "-R", "u=rX,g=rX", "/opt/docker"]
RUN ["chmod", "u+x,g+x", "/opt/docker/bin/to-do-list-api"]
RUN ["chmod", "u+x,g+x", "/opt/docker/bin/private-methods"]

FROM openjdk:11.0.7
USER root
RUN id -u demiourgos728 1>/dev/null 2>&1 || (( getent group 0 1>/dev/null 2>&1 || ( type groupadd 1>/dev/null 2>&1 && groupadd -g 0 root || addgroup -g 0 -S root )) && ( type useradd 1>/dev/null 2>&1 && useradd --system --create-home --uid 1001 --gid 0 demiourgos728 || adduser -S -u 1001 -G root demiourgos728 ))
WORKDIR /opt/docker
COPY --from=stage0 --chown=demiourgos728:root /opt/docker /opt/docker
EXPOSE 9000
USER 1001:0
ENTRYPOINT ["/opt/docker/bin/to-do-list-api"]
CMD []

FROM localstack/localstack:2.0

USER localstack
COPY --chown=localstack localstack/entrypoint.d/ /etc/localstack/init/ready.d/

RUN chmod u+x /etc/localstack/init/ready.d/v1.00_dynamodb.sh
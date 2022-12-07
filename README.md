# caseservices
Demo porject. Generates court-like cases and shows them via socket connection

This demo project demonstrates microservice interaction using Kafka as messsage broker.
Two services: one is generator of court case-like text (generator is ML model created by means of Python tensorflow/keras libs), other is consumer
and persistence layer: it saves generated cases in DB and then show them via WebSocket.

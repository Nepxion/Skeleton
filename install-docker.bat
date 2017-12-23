@echo on
@echo =============================================================
@echo $                                                           $
@echo $                      Nepxion Skeleton                     $
@echo $                                                           $
@echo $                                                           $
@echo $                                                           $
@echo $  Nepxion Technologies All Right Reserved                  $
@echo $  Copyright(C) 2017                                        $
@echo $                                                           $
@echo =============================================================
@echo.
@echo off

@title Nepxion Skeleton
@color 0a

@rem 删除target，有时候mvn会clean失败，需事先强制删除target
rmdir /s/q skeleton-spring-cloud\target

@rem 执行相关模块的install
call mvn clean install -DskipTests -pl skeleton-engine,skeleton-spring-cloud -am

@rem 安装Docker镜像
cd skeleton-spring-cloud

set DOCKER_HOST=tcp://localhost:2375
@rem set DOCKER_HOST=tcp://192.168.99.100:2376
@rem set DOCKER_CERT_PATH=C:\Users\Neptune\.docker\machine\certs

call mvn package docker:build -DskipTests && java -jar target/skeleton-spring-cloud-1.0.0.jar

pause
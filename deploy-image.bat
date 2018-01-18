@echo on
@echo =============================================================
@echo $                                                           $
@echo $                      Nepxion Skeleton                     $
@echo $                                                           $
@echo $                                                           $
@echo $                                                           $
@echo $  Nepxion Studio All Right Reserved                        $
@echo $  Copyright (C) 2017-2050                                  $
@echo $                                                           $
@echo =============================================================
@echo.
@echo off

@title Nepxion Skeleton
@color 0a

@set REGISTRY_URL=registry.cn-hangzhou.aliyuncs.com
@set REPOSITORY_NAME=nepxion/skeleton
@set USER_NAME=nepxion
@set IMAGE_NAME=skeleton-service
@set IMAGE_VERSION=latest

@echo Please input password of username=%USER_NAME% for %REGISTRY_URL%:
call docker login --username=%USER_NAME% %REGISTRY_URL%
call docker rmi %REGISTRY_URL%/%REPOSITORY_NAME%:%IMAGE_VERSION%
call docker tag %IMAGE_NAME%:%IMAGE_VERSION% %REGISTRY_URL%/%REPOSITORY_NAME%:%IMAGE_VERSION%
call docker push %REGISTRY_URL%/%REPOSITORY_NAME%:%IMAGE_VERSION%

pause
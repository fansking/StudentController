
let myApp = angular.module("myApp",[]);
myApp.controller("myController",function ($scope, $http) {
    //获取数据
    /*定义包含所有方法的所有属性的对象数组，包括
     *  path：方法的路径
     *  method：表单提交数据方式
     *  params:不同提交方式的变量属性数组
     *  summary:方法作用简介
     *  description:方法作用解释
     *  response:方法响应页面内容
     *  fun:方法实现的具体函数
     *
     * */
    $scope.operatios=[];
    //以对象形式定义输入变量
    $scope.inputValue={
        "path":{},
        "query":{},
        "body":{}
    };
    $scope.flag=[];
    //获取此服务的swagger的json文件并对其解析，得到更友好的对象形式

        $scope.MySwagger = JSON.parse('{"swagger":"2.0","info":{"description":"流程Swagger","version":"1.0.0","title":"测试影像流程","termsOfService":"http://localhost:8081/","contact":{"name":"jiaxiangyang"},"license":{}},"host":"localhost:8081","basePath":"/process/","tags":[{"name":"测试服务","description":"测试服务"}],"paths":{"/start/test1024":{"post":{"tags":["启动流程"],"summary":"启动流程","description":"通过定义Id启动流程","operationId":"startUsingPOST","consumes":["application/json"],"produces":["*/*"],"parameters":[{"in":"body","name":"InputData","description":"输入数据","required":true,"schema":{"$ref":"#/definitions/InputData"}}],"responses":{"200":{"description":"OK"},"201":{"description":"Created"},"401":{"description":"Unauthorized"},"403":{"description":"Forbidden"},"404":{"description":"Not Found"}}}}},"definitions":{"Data":{"properties":{"accessType":{"type":"string","enum":["FILE","URL","FOLDER","DATABASE"]},"address":{"type":"string"},"name":{"type":"string"},"productId":{"type":"string"}}},"InputData":{"properties":{"inputFile":{"description":"输入图像","$ref":"#/definitions/Data"},"inputFile2":{"description":"输入图像","$ref":"#/definitions/Data"}}}}}'
        );
        $scope.BasePath = $scope.MySwagger.host+$scope.MySwagger.basePath;
        for (let i in $scope.MySwagger.paths) {
            let operation={};
            for (let j in $scope.MySwagger.paths[i]) {
                operation.path=i;
                operation.method=j;
                operation.params={
                    "path":[],
                    "query":[],
                    "body":{}
                };
                angular.forEach($scope.MySwagger.paths[i][j].parameters, function (each) {
                    if(each.in==="path"){operation.params.path.push(each);}
                    if(each.in==="query"){operation.params.query.push(each);}
                    if(each.in==="body"){operation.params.body=each;operation.params.body.datavirable=[];}
                });
                let BodyType=operation.params.body.schema.$ref;
                array=BodyType.split("/");
                console.log($scope.MySwagger[array[1]][array[2]]);
                let bodyValue=$scope.MySwagger[array[1]][array[2]].properties;

                for(let b in bodyValue){

                    if(bodyValue[b].$ref=="#/definitions/Data"){
                        operation.params.body.datavirable.push(b);
                    }

                }
                operation.summary=$scope.MySwagger.paths[i][j].summary;
                operation.description=$scope.MySwagger.paths[i][j].description;
                operation.response=$scope.MySwagger.paths[i][j].response;
                $scope.operatios.push(operation);

            }
        }

        $scope.replacePathVariables=function(path,inputValue){
            for(let k in inputValue.path)
            {
                path=path.replace("{"+k+"}",inputValue.path[k]);

            }
            return path;
        };
        $scope.Init=function (count) {
            for(let n=0;n<$scope.flag.length;n++)
            {
                $scope.flag[n]=false;
            }
            $scope.flag[count]=true;
            $scope.inputValue={
                "path":{},
                "query":{},
                "body":{}
            };
        }
        //将方法实现的具体函数放入对象中
        for(let k =0;k<$scope.operatios.length;k++)
        {
            $scope.flag.push(false);
            $scope.operatios[k].Fun=(function () {
                $scope.inputValue.body={"id":3,"name":"吕","age":16};
                let newUrl=$scope.replacePathVariables($scope.operatios[k].path, $scope.inputValue);
                $http({
                    url: newUrl,
                    method: $scope.operatios[k].method,
                    params: $scope.inputValue.query,
                    data: $scope.inputValue.body,
                    contentType:"application/json"
                }).then(
                    function success(response) {
                        $scope.student = response.data;
                        $scope.message = "插入成功";
                        console.log("$scope.student");

                    }, function error(response) {
                        $scope.message = "插入失败";//响应错误的处理方法体
                    });
            })
        }

   
})
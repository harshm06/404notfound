(function(){
  'use-strict'
  angular.module('myApp',[])
         .controller('web2Controller',web2Controller)

  function web2Controller($http,$scope,$filter,$injector){
console.log('hello');
 $scope.heart_data = ['Yes','No'];
 $scope.set = function(){
   $scope.id = null;
   $scope.next_appoint = null;
   $scope.blood_pressure = null;
   $scope.sugar = null;
   $scope.thyroid = null;
   $scope.medicines = null;
   $scope.height =null;
   $scope.weight = null;
   $scope.heart_disease =null;
   $scope.remark = null;
 }
 $scope.set();
  $scope.getdata = function(){
    $http({
      method:'GET',
      url:'http://192.168.43.37:8000/health/dropdown/',
      }).then(function(response){
      console.log(response);
      $scope.data = response.data;
      console.log($scope.data);
      $scope.msg = "Post Data Submitted Successfully!";
           $scope.statusval = response.status;
           $scope.statustext = response.statusText;
           $scope.res = response.data;
           $scope.headers = response.headers();
    },function(response){
      $scope.msg = "Service not Exists";
      $scope.statusval = response.status;
      $scope.headers = response.headers();
    });
  }

$scope.getdata();
$scope.submit = function(){
  

  var daata = {
    id: $scope.id,
    next_appoint: $scope.next_appoint,
    blood_pressure:$scope.blood_pressure,
    sugar:$scope.sugar,
    thyroid:$scope.thyroid,
    medicines:$scope.medicines,
    height:$scope.height,
    weight:$scope.weight,
    heart_disease:$scope.heart_disease,
    remark:$scope.remark
  }

  for( var x in daata)
  {
    if(daata[x]==""||daata[x]==null||daata[x]==undefined)
    {
      swal("invalid data", "You clicked the button!", "error");
      return;
    }
  }

console.log(daata);
  $http.post('http://192.168.43.37:8000/health/insert/',daata).then(function(response){
    console.log(response);
     swal("success!","your data is recorded","success")
              $scope.msg = "Post Data Submitted Successfully!";
              $scope.statusval = response.status;
              $scope.statustext = response.statusText;
              $scope.res = response.data;
              $scope.headers = response.headers();
  },function(response){
     $scope.msg = "Service not Exists";
              swal("error!","something went wrong","error")
              $scope.statusval = response.status;
              $scope.headers = response.headers();
            });
}
  }
})();

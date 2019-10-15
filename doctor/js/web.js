(function(){
   'use-strict'
  angular.module('myApp',[])
         .controller('webController',webController);
function webController($http,$scope,$filter,$injector){
  // $scope.name = "samyak";
  $scope.gen_data =['Male','Female'];
  $scope.blood_group_data = ['A+','A-','B+','B-','AB+','AB-','O+','O-'];
  $scope.family_doctor_data = [{sno: 1 , value: "Yes" },{sno: 0 , value: "No"}];
$scope.set = function(){
     $scope.username = null;
     $scope.name = null;
     $scope.age = null;
     $scope.gender = null;
     $scope.email = null;
     $scope.mobile_no = null;
     $scope.emergency_contact = null;
     $scope.address = null;
     $scope.pre_Health = null;
     $scope.blood_group = null;
     $scope.family_doctor = null;
     $scope.doc_name = null;
     $scope.doc_no = null;
     $scope.doc_email = null;
     $scope.password = null;
     $scope.confirm_password = null;
     $scope.showDoctor = false;
  }
  $scope.set();
     $scope.changeDoctor = function(){
       if($scope.family_doctor == 0)
       {
         $scope.showDoctor = false;
       }
       else {
         $scope.showDoctor = true;
       }
     }

    $scope.submit = function(){
  // console.log($sc);
    var data={
      username:$scope.username,
      name : $scope.name,
      age:$scope.age,
      gender:$scope.gender,
      email:$scope.email,
      mobile_no:$scope.mobile_no,
      emergency_contact:$scope.emergency_contact,
      address:$scope.address,
      pre_Health:$scope.pre_Health,
      blood_group:$scope.blood_group,
      family_doctor:$scope.family_doctor,
      doc_name:$scope.doc_name,
      doc_no:$scope.doc_no,
      doc_email:$scope.doc_email,
      password:$scope.password
       }
       console.log(data);
         if(data['family_doctor']== 1)
         {
           for( var x in data)
           {
             if( data[x]== null|| data[x]==undefined || data[x]=="")
             {
               console.log(data[x]);
               console.log(x);
               console.log("hello1");
               swal("invalid!","fields can not be empty","warning");
               return;
             }
           }
         }
         else if(data['family_doctor']== 0|| data['family_doctor']==null)
         {
           for( var x in data)
           {
            if(x!='doc_no'&& x!='doc_name'&& x!='doc_email' && x!='family_doctor')
             {
             if( data[x]== null|| data[x]==undefined || data[x]=="")
             {
               console.log("hello");
               console.log(data[x]);
               console.log(x);
               swal("invalid!","fields can not be empty","warning");
               return;
             }
            }
           }
         }

         if($scope.password != $scope.confirm_password)
         {
           swal("invalid!","Password does not Match","warning");
           return;
         }

       console.log(data);
       $http.post('http://192.168.43.37:8000/health/register/',JSON.stringify(data)).then(function(response){
         console.log(response);
         swal("success!","your data is recorded","success")
              $scope.msg = "Post Data Submitted Successfully!";
              $scope.statusval = response.status;
              $scope.statustext = response.statusText;
              $scope.res = response.data;
              $scope.headers = response.headers();

          }, function(response) {
              $scope.msg = "Service not Exists";
              swal("error!","something went wrong","error")
              $scope.statusval = response.status;
              $scope.headers = response.headers();

          });
    }

console.log("hello samyak");
}

})();

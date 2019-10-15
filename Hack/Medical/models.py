from django.db import models

class user_details(models.Model):
   name=models.CharField(max_length=100)
   age=models.IntegerField()
   mobile_no=models.CharField(max_length=100)
   blood_group=models.CharField(max_length=100)
   email=models.CharField(max_length=100)
   gender=models.CharField(max_length=20)
   address=models.TextField()
   pre_Health=models.TextField()
   family_doctor=models.IntegerField(default=0)
   doc_no=models.CharField(max_length=120 , blank=True, null=True)
   doc_name=models.CharField(max_length=150 , blank=True, null=True)
   doc_email=models.CharField(max_length=150 ,blank=True, null=True)
   emergency_contact=models.CharField(max_length=100)
   warn=models.IntegerField(default=0)


class login_details(models.Model):
	link=models.ForeignKey(user_details, on_delete=models.CASCADE)
	username=models.CharField(max_length=100)
	password=models.CharField(max_length=100)



class check_up(models.Model):
   link1=models.ForeignKey(user_details, on_delete=models.CASCADE)
   final_remark=models.CharField(max_length=150)
   blood_pressure=models.CharField(max_length=150)
   sugar=models.CharField(max_length=150)
   thyroid=models.CharField(max_length=150)
   medicines=models.TextField()
   height=models.CharField(max_length=150)
   weight=models.CharField(max_length=150)
   heart_disease=models.CharField(max_length=150)
   today_date=models.CharField(max_length=100)
   next_date=models.CharField(max_length=100)
   status = models.CharField(max_length=50, blank=True, null=True, default='INSERT')

class hospital(models.Model):
   status=models.CharField(max_length=100)
   

from django.shortcuts import render
from django.http import Http404
from django.http import HttpResponse
from rest_framework.views import APIView
from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework import status
from django.http import JsonResponse
from django.conf import settings
import json
from django.db.models import Q, F
from datetime import date
import yagmail
from Medical.models import user_details,login_details,check_up

def register(request):
	if request.method == 'POST' :
		data = json.loads(request.body)
		q1 = login_details.objects.filter().values_list('username', flat=True)
		q2 = login_details.objects.filter(username=data['username']).values_list('username', flat=True)
		if (len(q2) >0):
			data2 = {'msg':"Username already exists."}
	
		elif data['family_doctor']==1:
			details= user_details.objects.create(name=data['name'],age=data['age'],mobile_no=data['mobile_no'],email=data['email'],gender=data['gender'],blood_group=data['blood_group'],address=data['address'],pre_Health=data['pre_Health'],family_doctor=data['family_doctor'],doc_no=data['doc_no'],doc_name=data['doc_name'],doc_email=data['doc_email'],emergency_contact=data['emergency_contact'])
			login_det=login_details.objects.create(username=data['username'],password=data['password'], link=details)
			yag = yagmail.SMTP('kaustubh.1822it1070@kiet.edu', 'Mx1b2jny3c2k')
			subject = "Health Care Verification Mail"
			contents = ["Your verification code is 1q2w3e4r5t6y"]
			yag.send(data['email'],subject,contents)
			data2={'msg':"Successfully Registered"}



		elif data['family_doctor']==0:
			details= user_details.objects.create(name=data['name'],age=data['age'],mobile_no=data['mobile_no'],email=data['email'],gender=data['gender'],blood_group=data['blood_group'],address=data['address'],pre_Health=data['pre_Health'],emergency_contact=data['emergency_contact'])
			login_det=login_details.objects.create(username=data['username'],password=data['password'], link=details)
			yag = yagmail.SMTP('kaustubh.1822it1070@kiet.edu', 'Mx1b2jny3c2k')
			subject = "Health Care Verification Mail"
			contents = ["Your verification code is 1q2w3e4r5t6y"]
			yag.send(data['email'],subject,contents)
			data2={'msg':"Successfully Registered"}


		return JsonResponse(data2)
	else:
		data2={'msg':"Some Error Occured"}
		return JsonResponse(data2)

def login(request):
	if request.method=='POST':
		data = json.loads(request.body)
		q1 = login_details.objects.filter().values_list('username', flat=True)
		obj= login_details.objects.all()
		for i in q1:
			if (i == data['username']):
					for x in obj:
						if(x.username == data['username'] and x.password == data['password']):
							name=login_details.objects.filter(username=data['username']).values('link__name', 'link__id')
							latest=check_up.objects.filter(link1__id=name[0]['link__id'], status='INSERT').values('final_remark','today_date','next_date')
							if len(latest) >0:
								data1={'status':name[0]['link__name'],"id":name[0]['link__id'], "remark":latest[0]['final_remark'], 'from_date':latest[0]['today_date'],"next_date":latest[0]['next_date']}

							else:
								data1={'status':name[0]['link__name'],"id":name[0]['link__id'], "remark":"No Checkups Yet"}
							break

						elif(x.username == data['username'] and x.password != data['password']):
							data1 = {'status':'wrong password'}
							break

					return JsonResponse(data1)

		data1={'status':'invalid username'}
		return JsonResponse(data1)


def panic_button(request):
	if request.method=='POST':
		data = json.loads(request.body)
		email=list(login_details.objects.filter(link__id=data['id']).values('link__doc_email', 'link__pre_Health','link__emergency_contact','link__name'))
		Email=email[0]['link__doc_email']
		pre_Health=email[0]['link__pre_Health']
		emergency_contact=email[0]['link__emergency_contact']
		name=email[0]['link__name']
		yag = yagmail.SMTP('kaustubh.1822it1070@kiet.edu', 'Mx1b2jny3c2k')
		subject = "ALERT ALERT ALERT"
		contents = ["HELP ! My name is "+name+" and I am your patient. I'm in a serious problem and in severe health issues, My previous health problems are : "+pre_Health+" . Do inform my emergency contact " + emergency_contact+" ."]
		# yag.send(Email,subject,contents)
		user=list(login_details.objects.filter(link__id=data['id']).values('link__age','link__blood_group','link__address','link__doc_no','link__doc_name','link__emergency_contact'))
		return JsonResponse(user,safe=False)

def dropdown(request):
	if request.method=='GET':
		user = user_details.objects.filter().exclude(name='HOSPITAL').values('id','name')
		return JsonResponse(list(user),safe=False)

def check_insert(request):
	if request.method=='POST':
		data=json.loads(request.body)
		next_date=data['next_appoint'].split('T')[0]
		previous=check_up.objects.filter(link1__id=data['id']).values()
		if len(previous) > 0:
			update=check_up.objects.filter(link1__id=data['id']).update(status='DELETE')
		insert=check_up.objects.create(final_remark=data['remark'], blood_pressure=data['blood_pressure'],sugar=data['sugar'],thyroid=data['thyroid'],medicines=data['medicines'],height=data['height'],weight=data['weight'],heart_disease=data['heart_disease'],link1=user_details.objects.get(id=data['id']),today_date=date.today(),next_date=next_date)
		response = {'status':'inserted'}
		return JsonResponse (response)

def profile(request):
	if request.method=='GET':
		id=request.GET['id']	
		details=check_up.objects.filter(link1__id=id,status='INSERT').values('link1__name','link1__age','link1__mobile_no','link1__blood_group','link1__email','link1__gender','link1__address','link1__pre_Health','link1__family_doctor','link1__doc_no','link1__doc_name','link1__doc_email','link1__emergency_contact','status').order_by('-status')
	return JsonResponse(list(details),safe=False)




def show_checkups(request):
	if request.method=='GET':
		id=request.GET['id']
		details=check_up.objects.filter(link1__id=id).values('final_remark','blood_pressure','sugar','thyroid','medicines','today_date','next_date','height','weight','heart_disease','status').order_by('-status')
		if(len(details)>0):
			return JsonResponse(list(details),safe=False)
		else:
			response = {'status':'No Checkups yet !!'}
			return JsonResponse(response,safe=False)


def panic_button_warn(request):

	if request.method == 'POST':
		data=json.loads(request.body)
		warn_count = user_details.objects.filter(id=data['id']).values('warn')
		return JsonResponse(list(warn_count),safe=False)


def hospital(request):
	if request.method=='GET':
		user = user_details.objects.filter().exclude(name='HOSPITAL').values('id','name')
		return JsonResponse(list(user),safe=False)

	if request.method=='POST':
		data=json.loads(request.body)
		id=data['id']
		warn_update=user_details.objects.filter(id=id).update(warn=F('warn')+1)
		return JsonResponse({"status":"success"})


def warn_reset(request):
	if request.method=='GET':
		id=request.GET['id']
		warn_reset=user_details.objects.filter(id=id).update(warn=0)
		return JsonResponse({"status":"success"})
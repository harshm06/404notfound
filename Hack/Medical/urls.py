from django.conf.urls import url
from .views import *

urlpatterns = [
    url(r'^register/$', register),
    url(r'^login/$', login),
    url(r'^panic_button/$', panic_button),
    url(r'^dropdown/$',dropdown),
    url(r'^insert/$',check_insert),
    url(r'^show/$',show_checkups),
    url(r'^profile/$',profile),
    url(r'^warn/$',panic_button_warn),
    url(r'^hospital/$',hospital),
    url(r'^warn_reset/$',warn_reset)
    ]
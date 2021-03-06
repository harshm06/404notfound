# Generated by Django 2.0 on 2019-10-14 13:40

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('Medical', '0003_user_details_emergency_contact'),
    ]

    operations = [
        migrations.CreateModel(
            name='check_up',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('final_remark', models.CharField(max_length=150)),
                ('blood_pressure', models.CharField(max_length=150)),
                ('sugar', models.CharField(max_length=150)),
                ('thyroid', models.CharField(max_length=150)),
                ('medicines', models.TextField()),
                ('height', models.CharField(max_length=150)),
                ('weight', models.CharField(max_length=150)),
                ('heart_disease', models.CharField(max_length=150)),
                ('status', models.CharField(blank=True, default='INSERT', max_length=50, null=True)),
                ('link1', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='Medical.user_details')),
            ],
        ),
    ]

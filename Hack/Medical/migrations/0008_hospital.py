# Generated by Django 2.0 on 2019-10-14 21:07

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('Medical', '0007_auto_20191014_1945'),
    ]

    operations = [
        migrations.CreateModel(
            name='hospital',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('status', models.CharField(max_length=100)),
            ],
        ),
    ]

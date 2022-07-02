import sys
import boto

def ec2_deregister_ami(ec2):
	images = ec2.get_all_images(owners="self", filters = {'name':str(sys.argv[2])})
	ec2.deregister_image(i.id, delete_snapshot=True)

ec2 = boto.ec2.connect_to_region(str(sys.argv[1]))
ec2_deregister_ami(ec2)
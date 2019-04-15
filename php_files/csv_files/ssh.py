#!/usr/bin/python
# simplest builtin python pseudo-tty for ssh password. meuh 
# http://unix.stackexchange.com/a/276385/119298
import os
import time
def run(cmd,*args):
    pid, fd = os.forkpty()
    if pid==0: # child
        os.execlp(cmd,*args)

    while True:
        data = os.read(fd,1024)
        print data
        if "password:" in data:    # ssh prompt
            os.write(fd,"student\n")
            time.sleep(1)

        elif data.endswith("$ "):  # bash prompt for input
            print "starting commands \n"
            #time.sleep(0.1)
            os.write(fd,"scp s1601745@lamp.ms.wits.ac.za:~/public_html/csv_files/file.csv csv_files/file.csv \n")
            #print os.read(fd,1024)
            os.write(fd,"python3 ~/csv_files/csv_2_latex.py\n")
            #print os.read(fd,1024)
            time.sleep(0.1)
            os.write(fd,"pandoc file.tex -o report.pdf\n")
            time.sleep(1)
            print os.read(fd,1024)
            os.write(fd,"scp report.pdf s1601745@lamp.ms.wits.ac.za:~/public_html/csv_files/report.pdf \n")
            time.sleep(0.5)
            print os.read(fd,1024)
            os.write(fd,"exit\n")
            #print os.read(fd,1024)

            #time.sleep(0.2)

            print "done!"
            break
        elif "ECDSA key fingerprint is" in data:
            os.write(fd,"yes\n")
            print os.read(fd,1024)
            

run("ssh", "ssh", "student@1611352.ms.wits.ac.za")
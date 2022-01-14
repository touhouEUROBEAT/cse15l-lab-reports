# CSE15L Lab 1 Guide

Greetings from the past. In this guide, I'll walk you through the process of finishing the first lab in CSE 15L, as well as some points that me and my class mates found
confusing. But before we start, I would like to bring to your attention that: 

***OpenSUSE is the best linux distro! Be sure to check out OpenSUSE.***

---

Below are the topics that we will be covering today:

```
* Installing VS Code 
* Remotely Connecting
* Trying Some Commands
* Moving Files with `scp`
* Setting an SSH Key
* Optimizing Remote Running
```

# Installing VS Code

In this step, we will be installing VS Code.

VS Code is an awesome editor made by Microsoft. It offer great cross-platform support, and is highly customizable through is built-in extension marketplace. It is simple,
versitile, and gets the job done. To download VS Code, follow the link here: [Link](https://code.visualstudio.com/download).

My fellow OpenSUSE users may also opt to use Microsoft's repo and let our package manager do the work for us, by using the following codes:

```
sudo rpm --import https://packages.microsoft.com/keys/microsoft.asc
sudo zypper addrepo https://packages.microsoft.com/yumrepos/vscode vscode
sudo zypper refresh
sudo zypper install code
```

If you run into any trouble installing VS Code, remember Google is your friend -- If you are facing this issue, chances are someone else has already had.

# Remotely Connecting

In this step, we will be connecting to UCSD's remote server using your class account, and this handy little tool called `ssh`.

First, look up your course-specifc account for CSE15L here:

[Link](https://sdacs.ucsd.edu/~icc/index.php) (Be sure to reset your password so as to activate your account, and allow about 15 minutes to let the activation go through)

Then, open your preferred terminal emulator and enter:

```
ssh cs15l*@ieng6.ucsd.edu
```

Be sure to replace * with the quarter in which you are in at the moment followed by your course specific identifier. For example, if you are currently in the winter
quarter of year 2023, and your identifier is xyz, then enter: (Notice that the course identifier is cs15l, not cse15l, for some reason)

```
ssh cs15lwi23xyz#ieng6.ucsd.edu
```

Since it's the first time you are connecting to the server, the terminal will likely tell you something along the line of the following:

```
The authenticity of host 'ieng6.ucsd.edu (128.54.70.227)' can't be established.
RSA key fingerprint is SHA256:ksruYwhnYH+sySHnHAtLUHngrPEyZTDl/1x99wUQcec.
Are you sure you want to continue connecting (yes/no/[fingerprint])?
```

If you see this message on your first connection to this server, it's expected, and you will be fine if you just say yes. If you see this message while connecting to a 
server that you frequent, be careful! Eve may be listening. 

Enter yes and press enter, and the terminal will proceed to ask for your password. Enter your password and press enter again. Don't be surprised if your terminal does not
show anything as you type in your password. It's just a safety feature.

If things go smoothly, welcome message will be displayed. If so, congradulations! You have just connected to a computer in the CSE basement. If not, double check if you
connected to the correct server, your password is correct, and you have waited about 15 minutes after activating your course account. You have double checked all of these, 
and the connection still doesn't go through, feel free to let your TA know, and s/he will help you out.



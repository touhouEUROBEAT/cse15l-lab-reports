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

VS Code is an awesome editor made by Microsoft. It offer great cross-platform support, and is highly customizable through is built-in extension marketplace. It is simple,
versitile, and gets the job done. To download, VS Code, follow the link here: [Link](https://code.visualstudio.com/download).

My fellow OpenSUSE users may also opt to use Microsoft's repo and let our package manager do the work for us, by using the following codes:

```
sudo rpm --import https://packages.microsoft.com/keys/microsoft.asc
sudo zypper addrepo https://packages.microsoft.com/yumrepos/vscode vscode
sudo zypper refresh
sudo zypper install code
```

---

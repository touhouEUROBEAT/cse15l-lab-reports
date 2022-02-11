## Step 1:

#### Show your .ssh/config file, and how you edited it (with ~~VScode, another program, etc~~ `vim`) ![](/img/report3_1.png) 

Since there are a lot of classes/students sharing the ieng6 server, it's inevitable that everyone's user name would be rather wordy so that they
make sense, and don't collide with each other. It's a neccesary evil from the server's persepective, but do we, the users really have to put up 
with the excessive key strokes every time we log in?

We don't have to. Using the best editor in the universe, I entered the following into a file named `config`, located at `~/.ssh/config`.

```
Host cs15l
    HostName ieng6.ucsd.edu
    User cs15lwi22aje (be sure to use your own username when you try it)
```

## Step 2:

#### Show the ssh command logging you into your account using just the alias you chose. ![](/img/report3_2.png)

As we can see, we are able to log into our account by using the alias `cs15l`, instead of our full user name, there by saving tremendous amount of 
key strokes.

## Step 3:

#### Show an scp command copying a file to your account using just the alias you chose. ![](/img/report3_3.png)

Notice that it works with scp as well. Here, we successfully transfered the file "foo2", which was not on our remote server, to the remote server 
using only the alias, instead of the full user name.

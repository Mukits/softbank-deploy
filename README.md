# 2023-Softbank Project

This is the Softbank_v1 project to test our knowledge in Secure programming and delivery!

The application uses Spring Boot and underlying Application Server provided by Apache Tomcat.

If you want to learn more about this type of project, look into Object-Relational Mapping (OMR) -- using Hibernate -- and about how to use Spring Boot to create applications in Java.

## Quick access
- [Dependencies](https://gitlab.com/cs32ss-module/2023-softbank-project#dependencies)
- [Comments on design choices](https://gitlab.com/cs32ss-module/2023-softbank-project#comments-on-design-choices)
- [Learning about OO and web-based implementation](https://gitlab.com/cs32ss-module/2023-softbank-project#learning-about-oo-and-web-based-implementation)
- [Common design patterns in Software Engineering](https://gitlab.com/cs32ss-module/2023-softbank-project#common-design-patterns-in-software-engineering)
- [Things to observe when creating value objects and entities](https://gitlab.com/cs32ss-module/2023-softbank-project#things-to-observe-when-creating-value-objects-and-entities)
- [General tips before working on this](https://gitlab.com/cs32ss-module/2023-softbank-project#general-tips-before-working-on-this)
- [Deploying the application to external server](https://gitlab.com/cs32ss-module/2023-softbank-project#deploying-the-application-to-external-server)
- [Important commands & features](https://gitlab.com/cs32ss-module/2023-softbank-project#important-commands-features)

## Dependencies

Java is a widely used programming language with many security features that we can explore.

The **repository pattern** that the application uses (JPA/Hibernate) is one of the most popular Java persistence patterns as well as MVC (Model-View-Controller).

- Java (JDK): https://www.oracle.com/uk/java/technologies/downloads/
- Spring Boot: https://spring.io/
    - Spring Initializr (generate pom.xml file – use with Maven): https://start.spring.io/
    - Internally, it uses Apache Tomcat: https://tomcat.apache.org/
- MS Visual Studio Code (VCS): https://code.visualstudio.com
- Apache Maven: https://maven.apache.org/ (uncompress directly to your c:\ in Win)
- Thymeleaf: https://www.thymeleaf.org/
- H2 Database Engine (Java): https://www.h2database.com/
- Hibernate: https://hibernate.org/
- Apache Log4j: https://logging.apache.org/log4j/

## Comments on design choices

- We have built a web application using Java as the main programming language
    - For building the project automatically, we have used Maven
- The application is creating the database transparently to us, using Object-Relational Mapping (ORM)
    - We only implement in the OO-based coding level: the database underpinnings will be created for us
        - The way this works is through JPA/Hibernate (JPA means Java Persistence API and it is the reference/specification whereas Hibernate is the implementation of JPA)
    - How does it do it? Look at the code base, on our domain objects (folders `edu\dss\softbank\domain` and `edu\dss\softbank\domain\vo` and see annotations like `@Entity`
        - These annotations will become TABLES (in a Database Mgmt System – DBMS)
    - One important thing to mention is the mapping uses the "repository" class for defining the ways to generate the methods to access the database (folder `edu\dss\softbank\repositories`)
        - For instance, the `UserRepository` class contains code to interact with users, ie, it will generate all the underlying SQL queries for you, automatically
- We chose to use the Spring Boot framework for running the web site: internally, it uses the Servlets technology to present us the pages and execute the Java code
    - It employs internally an Application Server (Apache Tomcat) that executes transparently to us when we run the project on VSCode (after it starts, we can point our browser to `http://localhost:8080`)
- In a Software Engineering level, the Spring Boot framework uses the MVC Pattern (Model-View-Controller): the Model is our domain/entity/value objects, the View are HTML pages (located in the `resources\template` folder), and the Controller are in `edu\dss\softbank\controllers`
    - The controllers are responsible for running the application
    - For the view we are using Thymeleaf, that seamlessly integrate with Spring Boot
- We are using the schematic for generating "functions" to us in the application level by leveraging **"entry-points"**
    - These entry-points also have specific annotations before the methods that expose the URLs in the application, eg, `@GetMapping("/register")` or `@PostMapping("/register/save")` for GET and POST methods in the HTML forms
- One could deploy an application like this in the cloud, using a known provider, eg, Google Cloud Platform (GCP), Amazon Web Services (AWS) or Microsoft Azure
    - Consult this link: https://blog.knoldus.com/spring-boot-launch-your-application-in-google-cloud-platform-gcp/ (using Spring Boot, Java and Maven to deploy an online application)
-  For learning more about everything that was just mentioned:
    - Using value objects with JPA: https://dev.to/peholmst/using-value-objects-with-jpa-27mi
    - Key mappings in JPA/Hibernate: https://thorben-janssen.com/primary-key-mappings-jpa-hibernate/
    - JPA vs Hibernate: https://www.geeksforgeeks.org/java-jpa-vs-hibernate/

## Learning about OO and web-based implementation

- Difference Between POJO, JavaBeans, DTO and VO: https://www.baeldung.com/java-pojo-javabeans-dto-vo (POJO: Plain Old Java Object, DTO: Data Transfer Object, VO: Value Object)
    - Also: https://stackoverflow.com/questions/1612334/difference-between-dto-vo-pojo-javabeans
- Further study and links:
    - Spring Boot: https://spring.io/quickstart
        - Learn Spring Boot (by Baeldung): https://www.baeldung.com/spring-boot
    - Hibernate ORM: https://hibernate.org/orm/
    - Beginners guide to MVC and Spring boot (recommended read) https://www.toptal.com/spring/beginners-guide-to-mvc-with-spring-framework

## Common design patterns in Software Engineering

Design Patterns are widely used in coding solutions. A nice introduction to different patterns is https://sourcemaking.com/design_patterns.

This website here (https://java-design-patterns.com/) is a **very good source** for understanding common design patterns in Java.

In our project, we are using the following design patterns to help us in our tasks:
- Repository Pattern
- Model-View-Controller (MVC)
- Data Transfer Object (DTO): is a(n) (anti) pattern actually introduced by Enterprise Java Beans.

And there are other patterns that we could be using, not yet being incorporated into the project:
- Singleton
- Builder
- Factory

A nice explanation of these patterns in action is given by this site: https://www.softwaretestinghelp.com/design-patterns-in-java/.

## Things to observe when creating value objects and entities

To map things without problems, when creating value objects, create proper methods. For instance, for class `Balance` that withholds a double value (`private final Double balance;`), besides the constructor, generate:
```
    ...
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Balance that = (Balance) o;
        return balance.equals(that.balance);
    }

    @Override
    public int hashCode() {
        return balance.hashCode();
    }

    @Override
    public String toString() {
        return balance.toString();
    }
    ...
```

## General tips before working on this

+ Run everything using the **LATEST** version (JDK, Maven, VSCode, Spring Boot, Hibernate, Thymeleaf, etc.)
+ Setup maven: download application, add to "Environment variables" (bin folder), and then put full path to mvn.cmd - eg, `C:\apache-maven-3.9.0\bin\mvn.cmd` on the VSCode properties
+ When creating the `ServiceImpl` NEVER forget to create the constructor WITH the repository!
+ **Never open anything in the target/ folder** - it is auto-generated each time it executes
+ Create proper Bean/POJO/VO and for value objects (mostly Getters, because of immutability)

## Deploying the application to external server

If you put yourself to this challenge, **you will learn a lot** of things related to deployment, VM, docker, CI/CD, runners, applications.

+ You will need a cloud subscription (I use [Kamatera Cloud Platform - Express](https://www.kamatera.com/express/compute/)), it costs $4/month for the least configuration (it asks your credit card - be extra **careful about hidden costs** related to your server).
    - Then you install a VM server (of your choice) and they will provide you with a SSH user/pwd for access (and an IP address)
        - I chose `Ubuntu 22.04` (1 cpu; 1024MB RAM; 10GB disk); you will be root, so take **extra caution**
            - You will have 1TB per month as download limit
            - [Kamatera's Dashboard](https://console.kamatera.com/dashboard)
        - In the server, you will need to install several things (after `apt update` as root)
            - I used **Uncomplicated Firewall** (ufw); there is a [very good manual in this link](https://www.cyberciti.biz/faq/how-to-configure-firewall-with-ufw-on-ubuntu-20-04-lts/)
            - Some commands I employed: 
            ```
            ufw status
            pico /etc/default/ufw --> check ipv6 enabled
            ufw allow ssh
            ufw default allow outgoing
            ufw default deny incoming
            ufw allow ssh
            ufw enable
            ufw status
            systemctl status ufw.service
            ufw status
            ufw allow 80/tcp comment 'Accept Apache'
            ufw allow 8080/tcp comment 'Accept Tomcat'
            ufw status
            ```
        - You will need other software: `apt install gcc g++ make nmap unzip default-jre default-jdk plocate`
            - Then install (manually) Apache Tomcat: `curl https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.71/bin/apache-tomcat-9.0.71.zip -o apache-tomcat-9.0.71.zip`
            - Set up paths: `pico ~/.bashrc` and add to the very end these lines:
                - `export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64`
                - `export CATALINA_HOME=/opt/apache-tomcat-9.0.71`
                - `source ~/.bashrc` (it will 'run' these paths on this session)
            - Follow [extra steps for Apache Tomcat](https://tomcat.apache.org/tomcat-10.1-doc/setup.html#Unix_daemon)
            - Steps for [running tomcat in server init](https://doc.digdash.com/xwiki/wiki/howtos/view/howtos/server/Linux_Set_DigDash_as_service/)
        - For CI/CD capabilities I used GitLab's Runners (pipelines)
            - You will need to install it on your server ([link1](https://docs.gitlab.com/runner/install/linux-manually.html) and [link2](https://docs.gitlab.com/runner/install/))
            ```
            sudo curl -L --output /usr/local/bin/gitlab-runner "https://gitlab-runner-downloads.s3.amazonaws.com/latest/binaries/gitlab-runner-linux-amd64"
            sudo chmod +x /usr/local/bin/gitlab-runner
            sudo useradd --comment 'GitLab Runner' --create-home gitlab-runner --shell /bin/bash
            sudo gitlab-runner install --user=gitlab-runner --working-directory=/home/gitlab-runner
            gitlab-runner register
            ```
            - You will need the **registration token** from GitLab (Go to your Project > Settings > CI/CD > Runners)
                - Read the Link "Install GitLab Runner and ensure it's running"
                    - Choose `https://gitlab.com` and `shell` and `prod` (example, you can choose other tags)
                    - Edit this runner and **select** `Indicates whether this runner can pick jobs without tags` and **unselect** `When a runner is locked, it cannot be assigned to other projects`
                    - I had a _minor bug_ whilst doing this
                        - Remove this from your root account: `rm -r /home/gitlab-runner/.bash_logout` (happened _just_ once)
            - The runner will allow you to commit/push your changes and it will 'self deploy' for you (it will look at `.gitlab-ci.yml` file)
                - You can see the pipelines on GitLab (Your Project > CI/CD > Pipelines); look also at **Jobs** to see outputs
        - To enable the gitlab-runner to execute 'root' commands, [I followed these instructions](https://karol-filipczuk.medium.com/gitlab-ci-cd-pipeline-run-script-via-ssh-to-remote-server-9594f326bc2f)
            - Basically, you need to create variables in GitLab: In GitLab, navigate to `Settings -> CI/CD -> Variables -> Expand -> Add Variable`
        - Interesting: [accessing Tomcat's manager via text (on the ssh)](https://www.baeldung.com/tomcat-manager-app)
        - Remember to stop CI/CD if not deploying applications on-line (there's a **monthly usage quota of 400 min** for deployments)
            - Go to GitLab's `Project Site > Settings > General > Visibility, project features, permissions`. Then expand the `Repository section`. Enable or disable the Pipelines toggle as required. It will 'disappear' from the web-site (CI/CD links).
- **DON'T FORGET**: remove all the internal links, secrets, etc. from the code (remember `SpringSecurity.java` and how H2 console was exposed for local testing??)

After doing all this, when you commit/push, technically, it will be available on-line on your server's tomcat version (and using the property files in this project), on port 8080.

As stated, for understanding the CI/CD on this project, see `.gitlab-ci.yml` file and understand what it does, and how it does it.
Mind that a lot of the variables there was defined in GitLab's settings.

## Important commands & features

### GNU/Linux
+ important folders
    - `/root`: root home
    - `/tmp`: temporary folder
    - `/home/USER`: other users home (replace USER by your user's username)
    - `/usr/home`: some things can be installed here or in `/var/opt`
    - `/var/log`: logs
+ `who`: shows users currently logged
+ see history: `history` and filter by something: `history | grep nmap`
   - delete the history (restart): `history -c`
+ see open ports in a server: `netstat -atnp`
+ running services in GNU/Linux: ` /etc/init.d/tomcat restart` (eg, for restarting tomcat server)
+ checking processes: `ps faux | grep NAME` (filter by NAME) and `top` (intermittently showing processes)
+ see disk quotas and mount points: `df -H`
+ check the firewall: `ufw status`
+ check the list of files opened by a network connection: `lsof -i` [more info on `lsof`](https://phoenixnap.com/kb/lsof-command)
+ discover linux 'flavour': `lsb_release -a` or `uname -a`
+ search for pattern in all .log files showing the name of the file if found the string pattern: `cat *.log | grep -RiH Pattern`
+ anything with `| less` after a command will paginate output for you (hit <SPACE> for next page, 'q' to quit)
+ tar: archiving multiple files in a single file (not necessarily compressing it)
    - `tar cvf allfiles.tar myfiles` (folder myfiles/ will be created with all its files)
    - `tar cvfz allfiles.tar.gz myfiles` (same, now it compress - note the change in its name)
    - `tar xvf allfiles.tar` (unpack allfiles.tar - a folder myfiles/ will be created)
    - `tar xvfz allfiles.tar.gz` (unpack and uncompress allfiles.tar.gz - same)
    - note that flags can appear in any order, eg, fzvc or vzfc
+ dump socket statistics: `ss -t -a`
+ manage systemd services: `systemctl start apache2.service` (assuming apache is running as a service)
+ terminating processes: `kill <PID>` (eg, `sudo kill 3332` - after you discovered the pid in `ps faux`) and `killall <NAME>` (eg, `sudo killall apache2`)
+ configure and display network interfaces: `ip a`
+ working with dates: `date` (see `man date` for more interesting examples)
+ working withh secure copying files (`FILENAME`) from source path (`SOURCE_PATH`) to destination (`DEST_PATH`):
    - `scp SOURCE_PATH/FILENAME USER@MACHINE:DEST_PATH`
        - you will need a valid username/pwd in the target/destination machine for this to work
+ Handling users (remember **don't run things as `root` user** - create users in your server)
    - Adding users: `adduser USER`
    - Switching to the newly user: `su - USER`
    - Checking which user is logged: `whoami`
+ Set ssh timeout (may be useful):
   - `pico /etc/ssh/sshd_config` (as root)
      - Look for (and change to): `MaxSessions 128`
      - Then restart service: `systemctl restart sshh`


### Git
+ Clone a repository: `git clone https://gitlab.com/group-name/project-name.git`
    - (change `group-name` and `project-name` to your URL - this will ask your user/pwd)
+ For adding/committing/pushing/pulling
   - Before, run these commands:
      ```
      git config --global user.name "Your Name"
      git config --global user.email "youremail@yourdomain.com"
      ```
   - Then, you can add files `git add FILE` 
   - Also commit: `git commit -m "MESSAGE"`
   - And push (up to repo): `git push`
   - Or pull (retrieve from repo): `git pull`
+ Removing things from the cache (like after updating the `.gitignore` file):
    - `git rm -r --cached *.class` (for example, removing all .class files from the cache)

### Docker
+ After installing docker following on-line instructions, create folder html/ and add a simple index.html file (to test nginx)
    - some useful commands:
    ```
    docker run --name docker-nginx -p 80:80 -d -v ./html:/usr/share/nginx/html nginx
    docker stop docker-nginx
    docker restart docker-nginx
    docker rm docker-nginx
    ```
    - To 'enter' a container: `docker exec -it CONTAINER SHELL`
        - Example: `docker exec -it cyberactive_apache_1 /bin/bash`
    - To see container logs: `docker logs -n 10 CONTAINER`
    - To run a container from an image:
        - `docker run -d -p 3307:3306 -e MYSQL_ROOT_PASSWORD=root mysql:8.0`
            - This basically means: bind port 3307 in **my** host machine with the port 3306 in the *container*
    - To "clean EVERYTHING" and start all over again (mind that the volumes map to actual data in your containers that are persisted):
    ```
    docker rm -f $(docker ps -a -q)
    docker rmi -f $(docker images -q)
    docker volume rm $(docker volume ls -q)
    docker system prune
    ```
    - To list volumes: `docker volume ls`
+ Visit [Docker Hub](https://hub.docker.com/) for (non-vulnerable) images

To run Docker compose in the VM, [you need to do additional commands](https://docs.docker.com/engine/install/linux-postinstall/)
For instance, creating `docker` group, adding the user to it (`usermod -aG docker $USER`), etc.
Then, assuming you have installed `docker-compose` (as root), you may issue this command:
   - `docker-compose up` to bring the contents of the file `docker-compose.yml` to fruition


### no-ip (Dynamic DNS service)
+ Instead of using that IP address provided by Kamatera (and that can change), one choice is to sign up to a Dynamic DNS provider like [no-ip](https://www.noip.com/)
    - There you associate the IP they provide with a hostname, then when it changes, you change in only one place
    - For this example, I have created [this DNS entry in my account](http://softbank.sytes.net/)
        - That's how adversaries may create malicious web-sites and send links to people to extract PII or other data - **of course I'm _not_ teaching you how to do this, just how it is done**


## Author
Ricardo M. Czekster

Aston University

meloczer@aston.ac.uk

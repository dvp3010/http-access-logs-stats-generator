# http-access-logs-stats-generator
Spruce Holdings Coding Exercise solution

### problem statement
Please complete the below problem for an arbitrary production HTTP server's log.  The only restriction is that you use Kotlin as your language of choice.

Given an [HTTP server access log]
(i.e. https://gist.github.com/zvozin/88fe5e242118efba48b66c43806da6ef), 
produce by-day rankings of:
- pages by unique hits
- pages by number of users
- users by unique page views

Do think through the performance/maintainability implications of the choices you're making.

### Prerequisites for running executable jar
- Java 1.8

### Setup for running executable jar
```sh
$ mkdir -p /tmp/spruceholdings
$ cd /tmp/spruceholdings
$ git clone https://github.com/dvp3010/http-access-logs-stats-generator.git
$ cd ./http-access-logs-stats-generator/executable
```

-------OR--------

You can download [http-access-logs-stats-generator-1.0.0-SNAPSHOT.jar](https://github.com/dvp3010/http-access-logs-stats-generator/raw/master/executable/http-access-logs-stats-generator-1.0.0-SNAPSHOT.jar) and open command prompt at downloaded location to run the program.

### Usage
```sh
$ java -jar http-access-logs-stats-generator-1.0.0-SNAPSHOT.jar /location/of/access/logs/csv/file
```

below is output of sample input file [access-logs.csv](https://github.com/dvp3010/http-access-logs-stats-generator/blob/master/access-logs.csv)
```sh
______________________________________________________________________________________
Pages By Unique Hits Stats
2017-09-30
	/index.html          5                    [user3, user5, user2, user4, user1]
	/checkout.html       4                    [user5, user3, user4, user2]
	/signup.html         4                    [user1, user2, user4, user5]
	/home.html           3                    [user3, user5, user1]
	/deactivate.html     2                    [user4, user2]
2017-09-29
	/home.html           5                    [user2, user4, user3, user1, user5]
	/deactivate.html     3                    [user5, user4, user1]
	/signup.html         2                    [user5, user4]
	/index.html          2                    [user1, user2]
2017-09-28
	/checkout.html       4                    [user5, user3, user2, user1]
	/index.html          3                    [user3, user2, user4]
	/signup.html         2                    [user2, user4]
	/home.html           2                    [user2, user4]
	/deactivate.html     2                    [user3, user1]
2017-09-27
	/signup.html         5                    [user2, user5, user3, user1, user4]
	/index.html          4                    [user4, user1, user2, user5]
	/home.html           4                    [user4, user1, user2, user5]
	/deactivate.html     3                    [user5, user2, user4]
	/checkout.html       2                    [user3, user5]

______________________________________________________________________________________
______________________________________________________________________________________
Pages By Number Of Users Stats
2017-09-30
	/index.html          14                   [user3, user5, user2, user4, user3, user2, user1, user5, user1, user2, user1, user4, user4, user2]
	/deactivate.html     7                    [user4, user4, user4, user2, user2, user4, user4]
	/signup.html         5                    [user1, user2, user4, user5, user2]
	/checkout.html       4                    [user5, user3, user4, user2]
	/home.html           3                    [user3, user5, user1]
2017-09-29
	/home.html           7                    [user2, user4, user3, user1, user1, user5, user2]
	/deactivate.html     7                    [user5, user5, user4, user4, user4, user4, user1]
	/index.html          3                    [user1, user2, user2]
	/signup.html         2                    [user5, user4]
2017-09-28
	/checkout.html       5                    [user5, user3, user2, user2, user1]
	/signup.html         5                    [user2, user4, user4, user2, user2]
	/index.html          4                    [user3, user2, user4, user3]
	/home.html           3                    [user2, user2, user4]
	/deactivate.html     2                    [user3, user1]
2017-09-27
	/signup.html         11                   [user2, user5, user2, user5, user3, user1, user4, user4, user5, user4, user1]
	/index.html          7                    [user4, user1, user2, user5, user5, user1, user2]
	/home.html           7                    [user4, user4, user4, user1, user2, user5, user4]
	/deactivate.html     3                    [user5, user2, user4]
	/checkout.html       2                    [user3, user5]

______________________________________________________________________________________
______________________________________________________________________________________
Users By Unique Page Views Stats
2017-09-30
	user2                4                    [/index.html, /deactivate.html, /signup.html, /checkout.html]
	user5                4                    [/checkout.html, /index.html, /home.html, /signup.html]
	user4                4                    [/deactivate.html, /index.html, /checkout.html, /signup.html]
	user1                3                    [/index.html, /signup.html, /home.html]
	user3                3                    [/index.html, /checkout.html, /home.html]
2017-09-29
	user1                3                    [/index.html, /home.html, /deactivate.html]
	user5                3                    [/deactivate.html, /signup.html, /home.html]
	user4                3                    [/home.html, /deactivate.html, /signup.html]
	user2                2                    [/home.html, /index.html]
	user3                1                    [/home.html]
2017-09-28
	user2                4                    [/index.html, /signup.html, /home.html, /checkout.html]
	user3                3                    [/index.html, /checkout.html, /deactivate.html]
	user4                3                    [/index.html, /signup.html, /home.html]
	user1                2                    [/deactivate.html, /checkout.html]
	user5                1                    [/checkout.html]
2017-09-27
	user5                5                    [/deactivate.html, /signup.html, /index.html, /checkout.html, /home.html]
	user2                4                    [/signup.html, /deactivate.html, /index.html, /home.html]
	user4                4                    [/home.html, /index.html, /signup.html, /deactivate.html]
	user1                3                    [/index.html, /home.html, /signup.html]
	user3                2                    [/checkout.html, /signup.html]

______________________________________________________________________________________
time took to complete program exec 129 ms

Process finished with exit code 0
```
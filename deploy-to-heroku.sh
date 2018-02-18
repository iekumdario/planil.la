#!/bin/bash

install_heroku () {
	echo "verifying heroku is installed"
	heroku --version

	if [ $? -ne 0 ]
	then
		npm install -g heroku-cli
		heroku --version
		if [ $? -ne 0 ]
		then
			echo "Error installing heroku"
			exit 1
		else
			
			echo "heroku installed successfully"
			return 0
		fi
	else
		echo "heroku is already installed"
		return 0
	fi
}

deploy_to_heroku () {
	
	HEROKU_NAME="desolate-earth-62016"
	set_heroku_login
	heroku git:remote -a $HEROKU_NAME
	git push heroku HEAD:master
	
	if [ $? -eq 0 ]
	then
		cleanup
		echo "Deployment sucessful 🚀  https://$HEROKU_NAME.herokuapp.com"
		exit 0
	else
		cleanup
		echo "Error deploying to heroku"
		exit 1
	fi
}

set_heroku_login () {
	echo "machine api.heroku.com
	  password c6cd2a5a-c9c5-4785-b260-0b2b70c7d39b
	  login jdquinterov@gmail.com
	machine git.heroku.com
	  password c6cd2a5a-c9c5-4785-b260-0b2b70c7d39b
	  login jdquinterov@gmail.com" >> ~/.netrc
}

cleanup () {
	echo "cleaning up"
	sed -i '/heroku.com/d' ~/.netrc
	sed -i '/password/d' ~/.netrc
	sed -i '/login jdquinterov@gmail.com/d' ~/.netrc
}

install_heroku
if [ $? -eq 0 ]
then
	echo "deploying to heroku..."
	deploy_to_heroku
fi



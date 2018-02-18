install_heroku
if [ $? -eq 0 ]
then
	deploy_to_heroku
fi
	

install_heroku () {
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
			return 0
		fi
	else
		return 0
	fi
}

deploy_to_heroku () {
	HEROKU_NAME="desolate-earth-62016"

	heroku git:remote -a $HEROKU_NAME
	git push -f heroku master

	if [ $? -eq 0 ]
	then
		echo "🚀  https://$HEROKU_NAME.herokuapp.com"
		exit 0
	else
		echo "Error deploying to heroku"
		exit 1
	fi
}



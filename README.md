# PhonepeMachineCoding Problem statement


## App Version Management System

Every smartphone user these days has lots of apps installed in their smartphones. These apps follow multiple iterations in its lifecycle which can range from fresh installs to updates of an existing app.

	* Install is done for cases where the phone doesn't have the app previously installed.
	* Updates are triggered when the app is preinstalled but a new feature is rolled out.

Design an app version management system for a mobile application, say PhonePe app.

We will assume that there is no marketplace like Playstore / AppStore exists and every App Owner can directly interact with the target device irrespective of operating system ( android / iOS )
To install any app, consumer can go to the website and directly install the app through online installer ( we’ll assume, this is something which is implemented )

### Real world example -

	* Install - Consumer just bought his / her first Mobile device and wants to use PhonePe. In such case a fresh Install will happen - always latest version supported will be installed. Consumer goes to phonepe website and selects install option given on website ( how it happens is outside the scope of problem statement )
	* Update - Above customer has installed the app and a few days later a new feature ( say dark mode ) is rolled out by PhonePe. In such cases, PhonePe will directly update the app on the phone.

### System Components -

	* App and App versions -
		App will have a list of versions, each version denoting a new file and metadata.
		Version will have some meta data associated with it, like the minimum supported operating system ( android / iOS ) version etc.
	* Roll out :: App admin can roll-out a new version from the backend. A roll-out can be either installing or updating the app -
		install - App is to be installed fresh in the device. We will install the app in the smartphone
		Update - takes a diff of installed version vs latest version and install the diff ( details in requirements part )
	* Rollout strategy - New version rollout can be done with different strategies.
		Beta rollout - roll out the app version only on specific devices
		Percentage rollout - roll out the app version on some percent on devices

### Requirements-

Below are various functions that should be supported with necessary parameters passed
Implement the above mentioned System components and their extensions (if possible)
Implement the following functionalities for an app version management system.
Note - We can use any of the available methods in the available capabilities section.

	* uploadNewVersion( // necessary params ) :
		Store the new version and version file byte stream in the system
	* createUpdatePatch( app, fromVersion, toVersion) :
		Create an update patch from source to target app version.
		Diff of fromVersion and toVersion will be a byte-stream or byte-array and same diff would be fired on the target device and pre-installed app will take care of installation
		Pre Installed app has capability to accept a byte-stream / byte-array and install it as an update / patch
	* releaseVersion(// necessary params) :
		will release a version as per the strategy. BetaRolloutStartergy will include rolling out a new app version to only a set of devices. 
	* isAppVersionSupported(// necessary params) :
		will check if given targetVersion supports the input device using info like like device android version, rollout strategies etc
	* checkForInstall(// necessary params) :
		Check whether a given h/w + OS supports the the app or not
	* checkForUpdates(// necessary params) :
		checks if an update is available for a given device.
	* executeTask(// necessary params) -
		will create an install or update task based on the method input. Can use methods from the list of available methods below.

### Available Capabilities -

We can assume below methods are available (can decide method arguments by yourselves) -

	* installApp - will flash the app file in the device
	* updateApp - will flash the diff pack in the device
	* createDiffPack - will consume 2 app files and create diff pack file from sourceFile to targetFile
	* uploadFile(fileContent) - will upload the file in some storage and return its url.
	* getFile(fileUrl) - returns a file content, which could then be flashed using either installApp or updateApp methods

### Code Expectations -

	- Everything has to be in memory.
	- Candidate can opt for any language for implementation
	- Simple and basic function are expected as entry point - no marks for providing fancy restful API or framework implementation
	- Because this is a machine coding round, try to at least complete the basic requirements with minimal required algorithm implementations.

### Evaluation criteria -

	- Working code
	- Code readability
	- Implementation of OOPs / OOD principles with proper Separation of concerns
	- Testability - a TDD approach ( not to be mixed with test cases )
	- Language proficiency
	- Test Cases
	- [execution time limit] 3 seconds (java)

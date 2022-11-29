DESCRIPTION = "A small image just capable running yttrium-app"

IMAGE_INSTALL = "packagegroup-core-boot ${CORE_IMAGE_EXTRA_INSTALL}"
IMAGE_LINGUAS = " "

IMAGE_INSTALL:append = " yttrium-app"

inherit core-image
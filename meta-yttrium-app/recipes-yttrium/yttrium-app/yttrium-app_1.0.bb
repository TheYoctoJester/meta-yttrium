SUMMARY = "Simple yttrium application"
SECTION = "examples"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
    file://yttrium.sh \
    file://yttrium.service \
"

RDEPENDS:${PN} = "bash"

inherit systemd

SYSTEMD_SERVICE:${PN} = "yttrium.service"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

YVERSION = "${@bb.utils.contains("DISTRO_FEATURES", "ydebug", "debug", "production", d)}"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/yttrium.sh ${D}${bindir}
	sed -i -e 's,development,${YVERSION},g' \
		-e 's,@YLED1@,${YLED1},g' \
		-e 's,@YLED2@,${YLED2},g' \
		-e 's,@YLED3@,${YLED3},g' \
		${D}${bindir}/yttrium.sh

	# deal with systemd unit files
	install -d ${D}${systemd_system_unitdir}
	install -m 0644 ${WORKDIR}/yttrium.service ${D}${systemd_system_unitdir}
	sed -i -e 's,@BASE_BINDIR@,${base_bindir},g' \
		-e 's,@BINDIR@,${bindir},g' \
		-e 's,@SBINDIR@,${sbindir},g' \
		${D}${systemd_system_unitdir}/yttrium.service
}
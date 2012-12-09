# py2exe + pygame
# base file from : http://www.pygame.org/wiki/Pygame2exe
# heavily modified to suit this project


from distutils.core import setup
import py2exe
import pygame
from modulefinder import Module

import sys
import os
import shutil
import operator
import pymunk

origIsSystemDLL = py2exe.build_exe.isSystemDLL


def isSystemDLL(pathname):
    if os.path.basename(pathname).lower() in ("libfreetype-6.dll",
        "libogg-0.dll", "sdl_ttf.dll"):
        return 0
    return origIsSystemDLL(pathname)  # return the orginal function

py2exe.build_exe.isSystemDLL = isSystemDLL


class pygame2exe(py2exe.build_exe.py2exe):
    def copy_extensions(self, extensions):
        #Get pygame default font
        pygamedir = os.path.split(pygame.base.__file__)[0]
        pygame_default_font = os.path.join(pygamedir,
            pygame.font.get_default_font())

        #Add font to list of extension to be copied
        extensions.append(Module("pygame.font", pygame_default_font))
        py2exe.build_exe.py2exe.copy_extensions(self, extensions)


if __name__ == '__main__':
    if operator.lt(len(sys.argv), 2):
        sys.argv.append('py2exe')

    if os.path.isdir("dist"):  # Erase previous destination dir
            shutil.rmtree("dist")

    path = os.path.split(pygame.__file__)[0]
    icon_file = os.path.join(path, 'pygame.ico')

    setup(
        cmdclass={'py2exe': pygame2exe},
        version="0.0.1",
        description="Work done for Wombat Project",
        name="Boids:Work",
        url="github.com/akshaykarthik/wombat",
        author="Drew S., Peter O., Akshay K.",
        author_email="akshaykarthik@gmail.com",
        license="copyrighted 2012-2013",
        # targets to build
        windows=[{
            'script': "main.py",
            'icon_resources': [(0, icon_file)],
            'copyright': ""
        }],
        options={'py2exe':
            {'optimize': 2,
             'bundle_files': 1,
             'compressed': True,
             'excludes': [],
             'packages': [],
             'dll_excludes': [],
             'includes': ["engine"]
            }},
        zipfile=None,
        data_files=[(os.path.dirname(pymunk.__file__) + '/chipmunk.dll')],
        dist_dir="dist"
        )

    if os.path.isdir('build'):  # Clean up build dir
        shutil.rmtree('build')

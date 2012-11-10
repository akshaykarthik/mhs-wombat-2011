-- main.lua

-- these libraries are returning an object thus need to be assigned to one
class 		= require "lib.hump.class"
gamestate 	= require "lib.hump.gamestate"
timer 		= require "lib.hump.timer"
loader 		= require "lib.loader"
-- lube is special in that it creates a global `lube` object that we use
require "lib.lube.LUBE"

-- this can also be replaced with 'tcpClient()' if we decide to use tcp
net = lube.udpClient()

states = {}

states["menu"] 			= require "states.menu"
states["settings"] 		= require "states.settings"
states["credits"] 		= require "states.credits"
-- states["single_menu"] 	= require "states.single_menu"
-- states["single_player"] 	= require "states.single_player"
-- states["network_menu"] 	= require "states.network_menu"
-- states["network_game"] 	= require "states.network_menu"

images = {}
sounds = {}


function love.load()
	love.graphics.print("loading...", 100, 100)
	loader.setBaseImageDir('assets/images')
	loader.setBaseAudioDir('assets/sounds')
	loader.setBaseFontDir('assets/fonts')


	loader.init()


	gamestate.registerEvents()
	gamestate.switch(states["menu"])
	--[[
	the base menu state should be able route the game through all other states
	ideally we have a splas screen state that goes first that redirects to the
	menu state...
	this is something that we need to work on
	--]]
end
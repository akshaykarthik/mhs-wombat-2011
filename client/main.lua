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


utils = require 'utils'
ui_button = require 'classes.ui.button'


states = {}
states["menu"] 			= require "states.menu"
states["credits"] 		= require "states.credits"
-- states["single_menu"] 	= require "states.single_menu"
-- states["single_player"] 	= require "states.single_player"
-- states["network_menu"] 	= require "states.network_menu"
-- states["network_game"] 	= require "states.network_menu"




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
	ideally we have a splash screen state that goes first that redirects to the
	menu state...
	this is something that we need to work on
	]]
end

--[[
this is basically the built in love.run module overrided b getting rid of
certain conditionals.

for example, the basic love.run has conditionals for whether love.load, love.run,
love.update, and love.draw existed. I removed those conditionals because we know
they exist.
]]
function love.run()
	math.randomseed(os.time())
	math.random() math.random()

	love.load(arg)

	local dt = 0

	while true do
		love.event.pump()
		for e,a,b,c,d in love.event.poll() do
			if e == "quit" and (not love.quit or not love.quit()) then
					if love.audio then
						love.audio.stop()
					end
					return
			end
			love.handlers[e](a,b,c,d)
		end

		love.timer.step()
		dt = love.timer.getDelta()

		love.update(dt)
		love.graphics.clear()
		love.draw()

		love.timer.sleep(0.001)
		love.graphics.present()
	end
end

local menu = gamestate.new()

local vars = {}


vars["credits"] = ui_button(0, 350, 512, 256)
vars["single"]  = ui_button(500, 350, 512, 256)
vars["multi"]   = ui_button(500, 550, 512, 256)

function menu:init()
    vars["credits"]:configure(loader.Image.menu.credits_base,
                                loader.Image.menu.credits_hover,
                                loader.Image.menu.credits_click)
    vars["single"]:configure(loader.Image.menu.singleplayer_base,
                                loader.Image.menu.singleplayer_hover,
                                loader.Image.menu.singleplayer_click)
    vars["multi"]:configure(loader.Image.menu.multiplayer_base,
                                loader.Image.menu.multiplayer_hover,
                                loader.Image.menu.multiplayer_click)
end

function menu:enter()
    love.graphics.setFont(loader.extFont.base_font())
end

function menu:update(dt)
    vars["credits"]:update(dt)
    vars["single"]:update(dt)
    vars["multi"]:update(dt)
end

function menu:draw()

    love.graphics.draw(loader.Image.menu.base_menu , 0, 0)

    vars["credits"]:draw()
    vars["single"]:draw()
    vars["multi"]:draw()

end


function menu:keyreleased(key)
	if key == "a" then
		gamestate.switch(states["credits"])
    end
end

return menu

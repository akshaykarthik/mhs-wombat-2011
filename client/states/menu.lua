local menu = gamestate.new()
local vars = {}

function menu:enter()
	love.graphics.setFont(loader.extFont.base_font(124))
end

function menu:draw()
    love.graphics.draw(loader.Image.menu.base_menu, 0, 0)
    love.graphics.print("Press A to go to the Credits", 10, 400)
    love.graphics.print("Press B to go to the Settings", 10, 600)
end

function menu:keyreleased(key)
	if key == "a" then
		gamestate.switch(states["credits"])
	elseif key == "b" then
		gamestate.switch(states["settings"])
	end
end

return menu
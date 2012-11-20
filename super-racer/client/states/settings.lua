local settings = gamestate.new()
local vars = {}


function settings:draw()
    love.graphics.print("This is where the settings go", 10, 10)
end

function settings:keyreleased(key)
	if key == "escape" then
		gamestate.switch(states["menu"])
	end
end

return settings
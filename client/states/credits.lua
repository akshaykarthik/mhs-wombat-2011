local credits = gamestate.new()
local vars = {}




function credits:draw()
    love.graphics.print("This is where the credits go", 10, 10)
end

function credits:keyreleased(key)
	if key == "escape" then
		gamestate.switch(states["menu"])
	end
end

return credits
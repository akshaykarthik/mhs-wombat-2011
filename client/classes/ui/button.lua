-- button.lua
local button = class{
    function(self, x, y, l, w)
        self.x = x
        self.y = y
        self.l = l
        self.w = w
    end
}

function button:configure(base, hover, click)
    self.base = base
    self.hover = hover
    self.click  = click
    self.img = self.base
end

function button:update( dt )
    if utils.mouseHit(self.x, self.y, self.l, self.w) then
        if love.mouse.isDown('l') then
            self.img = self.click
        else
            self.img = self.hover
        end
    else
        self.img = self.base
    end

end

function button:draw(  )
    love.graphics.draw(self.img, self.x, self.y)
end

return button

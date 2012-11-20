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
    self.status = "none"
end

function button:update( dt )
    if utils.mouseHit(self.x, self.y, self.l, self.w) then
        if love.mouse.isDown('l') then
            self.status = "clicked"
        else
            self.status = "hover"
        end
    else
        self.status = "none"
    end
end

function button:draw(  )
    if self.status == "none" then
        self.img = self.base
    elseif self.status == "hover" then
        self.img = self.hover
    else
        self.img = self.click
    end

    love.graphics.draw(self.img, self.x, self.y)
end

return button

-- utils.lua
local utils = {}

--[[
this function checks whether point x,y lies in a box
with corner u,v and lenth l, width w.

this can be used for checking whether the mouse is over a button
]]
function utils.pointInBox( x, y, u, v, l, w )
    return (x > u and x < u + l) and
            (y > v and y < v + w)
end

function  utils.mouseHit( x, y, l, w )
    return utils.pointInBox(love.mouse.getX(),
                        love.mouse.getY(),
                        x,
                        y,
                        l,
                        w
                    )
end

return utils

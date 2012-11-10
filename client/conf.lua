-- conf.lua
function love.conf(t)
	-- basic configurations
    t.title = "Super Race"
    t.author = "Akshay, Drew, and Peter"
    t.url = "https://github.com/akshaykarthik/wombat"

    -- identity configurations
    t.identity = nil
    t.version = "0.8.0"
    t.console = false
    t.release = true

    -- screen configurations
    t.screen.fullscreen = false
    t.screen.width = 1024
    t.screen.height = 768
    t.screen.vsync = true
    t.screen.fsaa = 0

    -- module configurations
    t.modules.mouse = true
    t.modules.keyboard = true
    t.modules.joystick = false

    t.modules.event = true
    t.modules.timer = true
    
    t.modules.audio = true
    t.modules.sound = true
    
    t.modules.graphics = true
    t.modules.image = true
    
    t.modules.physics = false

end

-- 获取方法签名特征
local methodKey = KEYS[1]
redis.log(redis.LOG_DEBUG, 'key is ', methodKey)
-- 调用脚本传入的限流大小
local limit = tonumber(ARGV[1])
-- 获取当前流量大小
local count = tonumber(redis.call('get', methodKey) or "0")
redis.log(redis.LOG_DEBUG, 'count is ', count)

-- 是否超出限流阈值
if count + 1 > limit then
    return false
else
    -- 当前访问数量+1，并设置过期时间
    redis.call("INCRBY", methodKey, 1)
    redis.call("EXPIRE", methodKey, 1)
    return true
end






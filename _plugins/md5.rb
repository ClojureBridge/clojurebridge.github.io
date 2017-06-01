require 'digest/md5'

module MD5
  def md5 input
    Digest::MD5.hexdigest input
  end
end

Liquid::Template.register_filter MD5

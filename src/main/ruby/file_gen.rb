#!/usr/bin/ruby

require 'optparse'

version = 1.0
options = {
  :num_nodes => 15,
  :max_edges => 5,
  :min_edges => 1,
  :isWeighted => false,
  :low_weight => 50,
  :high_weight => 450,
  :file => "output.graph"
}
OptionParser.new do |opts|
  opts.banner = "Usage: file_gen.rb [options]"
  opts.separator ""
  opts.separator "Specific options:"

  opts.on("-nNODES", "--num-nodes=NODES", "Number of Nodes in the Network.") do |nodes|
    options[:num_nodes] = nodes.to_i
  end

  opts.on("-xMAX", "--max-edges=MAX", "Maximum out edges per node") do |max|
    options[:max_edges] = max.to_i
  end

  opts.on("-mMIN", "--min-edges=MIN", "Minimum out edges per node") do |min|
    options[:min_edges] = min.to_i
  end

  opts.on("-w", "--isWeighted", "Sets that the generated graph is isWeighted.") do
    options[:isWeighted] = true
  end

  opts.on("-lLOWER", "--lower-weight=LOWER", "Sets the minimum weight") do |lower|
    options[:lower_weight] = lower.to_i
  end

  opts.on("-uUPPER", "--upper-weight=UPPER", "sets the maxiumum weight") do |upper|
    options[:upper_weight] = upper.to_i
  end

  opts.on("-o", "--output=FILE", "File to output to") do |file|
    options[:file] = file
  end

  opts.on("-h", "--help", "Prints this help") do
    puts opts
    exit
  end

  opts.on("--version", "Show version") do
    puts version
    exit
  end

  opts.separator ""
  opts.separator "Copyright (C) 2019 Isaac Griffith"
end.parse!

p options

edges = Hash.new
(1..options[:num_nodes]).each do |x|
  label = "#{rand(0..255)}.#{rand(0..255)}.#{rand(0..255)}.#{rand(0..255)}"
  edges[label] = []
end

edges.each do |node, list|
  other = edges.keys.shuffle
  other.delete(node)
  rand(options[:min_edges]..options[:max_edges]).times do |x|
    break if other.empty?
    list << other[0]
    other.delete_at(0)
  end
end

file = File.new(options[:file], "w")
edges.each do |node, list|
  String line = "#{node}:"
  list.each do |l|
    line += " #{l}"
    if options[:isWeighted]
      line += "(#{rand(options[:lower_weight]..options[:upper_weight])})"
    end
  end
  file.puts line
end
file.close

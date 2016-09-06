set :application, "test-docker-app"
set :repository,  "git@github.com:shirishwebonise/Webonise-Training-Assignments.git"
set :branch, "DevOps"

set :normalize_asset_timestamps, false

set :user_name, "webonise"

set :copy_dir, "/home/#{user_name}/tmp"
set :remote_copy_dir, "/tmp"

set :scm, :git

#role :web, "your web-server here"                          # Your HTTP server, Apache/etc
role :app, "localhost"                          # This may be the same as your `Web` server
#role :db,  "your primary db-server here", :primary => true # This is where Rails migrations will run
#role :db,  "your slave db-server here"

#after "deploy:restart", "deploy:cleanup"

set :deploy_to, "/home/#{fetch(:user_name)}/Documents/#{fetch(:application)}"

set :deploy_via, :copy

before :deploy, "deploy:create_directory"

namespace :deploy do
  task :create_directory do
    run "mkdir -p '#{deploy_to}/releases'"
  end
end
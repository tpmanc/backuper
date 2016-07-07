var gulp = require('gulp'); 
var less = require('gulp-less');
var concat = require('gulp-concat');
var csso = require('gulp-csso');
var autoprefixer = require('gulp-autoprefixer');
var uglify = require('gulp-uglify');

gulp.task('less', function() {
    gulp.src(['../src/main/webapp/resources/less/main.less'])
        .pipe(less())
        .pipe(autoprefixer({
            browsers: ['last 2 versions'],
            cascade: false
        }))
        .pipe(csso())
        .pipe(gulp.dest('../src/main/webapp/resources/production/main.min.css'));
});

gulp.task('js', function() {
    gulp.src([
            '../src/main/webapp/resources/js/libs/jquery-3.0.0.min.js',
            '../src/main/webapp/resources/js/main.js'
        ])
        .pipe(concat('main.min.js'))
        .pipe(uglify())
        .pipe(gulp.dest('../src/main/webapp/resources/production/'));
});
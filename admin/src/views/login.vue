<template>
  <div class="main-container">
    <div class="main-content">
      <div class="row">
        <div class="col-sm-10 col-sm-offset-1">
          <div class="login-container">
            <div class="center">
              <h1>
                <i class="ace-icon fa fa-leaf green"></i>
                <span class="">控台登录</span>
              </h1>
              <h4 class="blue" id="id-company-text">&copy; Course-Online</h4>
            </div>

            <div class="space-6"></div>

            <div class="position-relative">
              <div id="login-box" class="login-box visible widget-box no-border">
                <div class="widget-body">
                  <div class="widget-main">
                    <h4 class="header blue lighter bigger">
                      <i class="ace-icon fa fa-coffee green"></i>
                      请输入用户名和密码
                    </h4>

                    <div class="space-6"></div>

                    <form>
                      <fieldset>
                        <label class="block clearfix">
                        <span class="block input-icon input-icon-right">
                            <input type="text" class="form-control" placeholder="用户名"/>
                            <i class="ace-icon fa fa-user"></i>
                        </span>
                        </label>

                        <label class="block clearfix">
                        <span class="block input-icon input-icon-right">
                            <input type="password" class="form-control" placeholder="密码"/>
                            <i class="ace-icon fa fa-lock"></i>
                        </span>
                        </label>

                        <div class="space"></div>

                        <div class="clearfix">
                          <label class="inline">
                            <input type="checkbox" class="ace"/>
                            <span class="lbl">记住我</span>
                          </label>

                          <!-- v-on:click => @click -->
                          <button type="button"
                                  class="width-35 pull-right btn btn-sm btn-primary"
                                  v-on:click="login()">
                            <i class="ace-icon fa fa-key"></i>
                            <span class="bigger-110">登录</span>
                          </button>
                        </div>

                        <div class="space-4"></div>
                      </fieldset>
                    </form>

                  </div><!-- /.widget-main -->
                </div><!-- /.widget-body -->
              </div><!-- /.login-box -->
            </div><!-- /.position-relative -->
          </div> <!-- /.login-container -->
        </div><!-- /.col -->
      </div><!-- /.row -->
    </div><!-- /.main-content -->
  </div><!-- /.main-container -->
</template>

<script>
  /*在<script> 标签中写的js代码是全局的, 会影响其他页面*/
  /* mounted 为vue的初始化函数, 类似于mounted, created之类的函数称为钩子函数  */
    export default {
        name: 'login',
        mounted: function() {
            $('body').removeClass('no-skin');
            $('body').attr('class', 'login-layout light-login');
        },
        methods: {
            login (){
                this.$router.push("/welcome");
            }
        }
    }
</script>

<style>

</style>

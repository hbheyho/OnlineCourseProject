<template>
  <div>
    <p>
      <button v-on:click="add()" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-edit"></i>
        新增
      </button>
      &nbsp;
      <button v-on:click="list(1)" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-refresh"></i>
        刷新
      </button>
    </p>

    <!-- 引用子组件, 前面的list是分页组件暴露出来的一个回调方法, 后面的list是course组件的list方法 -->
    <pagination ref="pagination" v-bind:list="list"></pagination>

    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
            <th>id</th>
            <th>名称</th>
            <th>概述</th>
            <th>时长/单位秒</th>
            <th>价格(元)</th>
            <th>封面</th>
            <th>级别</th>
            <th>收费</th>
            <th>状态</th>
            <th>报名数</th>
            <th>顺序</th>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>

      <tr v-for="course in courses">
              <td>{{course.id}}</td>
              <td>{{course.name}}</td>
              <td>{{course.summary}}</td>
              <td>{{course.time}}</td>
              <td>{{course.price}}</td>
              <td>{{course.image}}</td>
              <td>{{COURSE_LEVEL | optionKV(course.level)}}</td>
              <td>{{COURSE_CHARGE | optionKV(course.charge)}}</td>
              <td>{{COURSE_STATUS | optionKV(course.status)}}</td>
              <td>{{course.enroll}}</td>
              <td>{{course.sort}}</td>
        <!-- 一行中的相关操作 -->
        <td>
          <div class="hidden-sm hidden-xs btn-group">

            <button v-on:click="edit(course)" class="btn btn-xs btn-info">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>

            <button v-on:click="del(course.id)" class="btn btn-xs btn-danger">
              <i class="ace-icon fa fa-trash-o bigger-120"></i>
            </button>

          </div>

          <div class="hidden-md hidden-lg">
            <div class="inline pos-rel">
              <button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
                <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
              </button>

              <ul
                class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                <li>
                  <a href="#" class="tooltip-info" data-rel="tooltip" title="View">
																			<span class="blue">
																				<i class="ace-icon fa fa-search-plus bigger-120"></i>
																			</span>
                  </a>
                </li>

                <li>
                  <a href="#" class="tooltip-success" data-rel="tooltip" title="Edit">
																			<span class="green">
																				<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																			</span>
                  </a>
                </li>

                <li>
                  <a href="#" class="tooltip-error" data-rel="tooltip" title="Delete">
																			<span class="red">
																				<i class="ace-icon fa fa-trash-o bigger-120"></i>
																			</span>
                  </a>
                </li>
              </ul>
            </div>
          </div>
        </td>
      </tr>

      </tbody>
    </table>

    <!-- 模拟框 -->
    <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">表单</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">

                    <div class="form-group">
                      <label class="col-sm-2 control-label">名称</label>
                      <div class="col-sm-10">
                        <input v-model="course.name" type="text"  class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">概述</label>
                      <div class="col-sm-10">
                        <input v-model="course.summary" type="text"  class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">时长/单位秒</label>
                      <div class="col-sm-10">
                        <input v-model="course.time" type="text"  class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">价格(元)</label>
                      <div class="col-sm-10">
                        <input v-model="course.price" type="text"  class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">封面</label>
                      <div class="col-sm-10">
                        <input v-model="course.image" type="text"  class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">级别</label>
                      <div class="col-sm-10">
                        <select v-model="course.level" class="form-control">
                          <option v-for="o in COURSE_LEVEL" v-bind:value="o.key">{{o.value}}</option>
                        </select>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">收费</label>
                      <div class="col-sm-10">
                        <select v-model="course.charge" class="form-control">
                          <option v-for="o in COURSE_CHARGE" v-bind:value="o.key">{{o.value}}</option>
                        </select>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">状态</label>
                      <div class="col-sm-10">
                        <select v-model="course.status" class="form-control">
                          <option v-for="o in COURSE_STATUS" v-bind:value="o.key">{{o.value}}</option>
                        </select>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">报名数</label>
                      <div class="col-sm-10">
                        <input v-model="course.enroll" type="text"  class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">顺序</label>
                      <div class="col-sm-10">
                        <input v-model="course.sort" type="text"  class="form-control">
                      </div>
                    </div>

            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            <button v-on:click="save()" type="button" class="btn btn-primary">保存</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

  </div>
</template>

<script>
    // 引入子组件
    import Pagination from "../../components/pagination";

    export default {
        name: "business-course",
        components: {Pagination},
        // 使用data定义组件内的变量,可用于做双向数据绑定
        // course变量用来绑定form表单的数据
        data: function () {
            return {
                course: {},
                courses: [],
                COURSE_LEVEL: COURSE_LEVEL,
                COURSE_CHARGE: COURSE_CHARGE,
                COURSE_STATUS: COURSE_STATUS,
            }
        },
        mounted: function () {
            let _this = this;
            _this.list(1);
            // sidebar激活函数一
            /*this.$parent.activeSidebar("business-course-sidebar");*/
        },
        methods: {
            /*
            * 弹出新增模拟框
            * */
            add() {
                let _this = this;
                // 防止受上一次编辑记录的影响
                _this.course = {};
                $("#form-modal").modal("show");
            },
            /*
             * 弹出编辑模拟框
             * */
            edit(course) {
                let _this = this;
                // 点击编辑之后, 在文本框中显示course
                // 保证对_this.course变量的修改不会影响到 传递过来的course变量
                _this.course = $.extend({}, course);
                $("#form-modal").modal("show");
            },
            /*
            *  展示课程列表
            * */
            list(page) {
                let _this = this;
                // 加载框
                Loading.show();
                // post 默认用json来向后端传递数据
                // _this.$refs.xxx 获取子组件变量/操作
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/course/list', {
                    page: page,
                    size: _this.$refs.pagination.size,
                }).then((response) => {
                    Loading.hide();
                    console.log("查询到课程结果:", response);
                    let res = response.data;
                    _this.courses = res.content.list;
                    // 将数据渲染到子组件
                    _this.$refs.pagination.render(page, res.content.total);
                })
            },
            /*
            *  新增课程数据
            * */
            save() {
                let _this = this;

                // 进行数据校验, 增加 1!=1, 来去掉最前面的 ||
                if ( 1 != 1
                        || !Validator.require(_this.course.name, "名称")
                        // ?c 解决 2000 ->2,000问题
                        || !Validator.length(_this.course.name, "名称", 1 , 50)
                        // ?c 解决 2000 ->2,000问题
                        || !Validator.length(_this.course.summary, "概述", 1 , 2000)
                        || !Validator.require(_this.course.price, "价格(元)")
                        // ?c 解决 2000 ->2,000问题
                        || !Validator.length(_this.course.image, "封面", 1 , 100)
                        || !Validator.require(_this.course.level, "级别")
                ){
                  return;
                }

                Loading.show();
                // post 默认用json来向后端传递数据
                _this.$ajax.post(process.env.VUE_APP_SERVER  + '/business/admin/course/save', _this.course
                ).then((response) => {
                    Loading.hide();
                    console.log("新增课程结果:", response);
                    let res = response.data;
                    if(res.success){
                        $("#form-modal").modal("hide");
                        _this.list(1);
                        toast.success("新增成功");
                    }else {
                        toast.error(res.message);
                    }

                })
            },
            /*
            *  删除课程数据
            * */
            del(id) {
                let _this = this;
                // 确认弹出框
                Confirm.show("删除后不可恢复!", function () {
                    Loading.show();
                    // post 默认用json来向后端传递数据
                    // 使用Restful请求在后面直接拼接
                    _this.$ajax.delete(process.env.VUE_APP_SERVER  + '/business/admin/course/delete/' + id
                    ).then((response) => {
                        Loading.hide();
                        console.log("删除课程结果:", response);
                        let res = response.data;
                        if(res.success){
                            toast.success("删除成功!");
                            _this.list(1);
                        }else {
                            toast.error("删除失败!");
                        }
                    });
                });
            }
        }
    }
</script>
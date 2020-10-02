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

    <!-- 引用子组件, 前面的list是分页组件暴露出来的一个回调方法, 后面的list是section组件的list方法 -->
    <pagination ref="pagination" v-bind:list="list"></pagination>

    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
            <th>id</th>
            <th>标题</th>
            <th>课程ID</th>
            <th>大章ID</th>
            <th>视频</th>
            <th>时长/单位秒</th>
            <th>收费 - C; 免费 - F</th>
            <th>顺序</th>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>

      <tr v-for="section in sections">
            <td>{{section.id}}</td>
            <td>{{section.title}}</td>
            <td>{{section.courseId}}</td>
            <td>{{section.chapterId}}</td>
            <td>{{section.video}}</td>
            <td>{{section.time}}</td>
            <td>{{section.charge}}</td>
            <td>{{section.sort}}</td>
        <!-- 一行中的相关操作 -->
        <td>
          <div class="hidden-sm hidden-xs btn-group">

            <button v-on:click="edit(section)" class="btn btn-xs btn-info">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>

            <button v-on:click="del(section.id)" class="btn btn-xs btn-danger">
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
                    <label class="col-sm-2 control-label">标题</label>
                    <div class="col-sm-10">
                      <input v-model="section.title" type="text"  class="form-control">
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">课程ID</label>
                    <div class="col-sm-10">
                      <input v-model="section.courseId" type="text"  class="form-control">
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">大章ID</label>
                    <div class="col-sm-10">
                      <input v-model="section.chapterId" type="text"  class="form-control">
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">视频</label>
                    <div class="col-sm-10">
                      <input v-model="section.video" type="text"  class="form-control">
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">时长/单位秒</label>
                    <div class="col-sm-10">
                      <input v-model="section.time" type="text"  class="form-control">
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">收费 - C; 免费 - F</label>
                    <div class="col-sm-10">
                      <input v-model="section.charge" type="text"  class="form-control">
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">顺序</label>
                    <div class="col-sm-10">
                      <input v-model="section.sort" type="text"  class="form-control">
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
        name: "section",
        components: {Pagination},
        // 使用data定义组件内的变量,可用于做双向数据绑定
        // section变量用来绑定form表单的数据
        data: function () {
            return {
                section: {},
                sections: []
            }
        },
        mounted: function () {
            let _this = this;
            _this.list(1);
            // sidebar激活函数一
            /*this.$parent.activeSidebar("business-section-sidebar");*/
        },
        methods: {
            /*
            * 弹出新增模拟框
            * */
            add() {
                let _this = this;
                // 防止受上一次编辑记录的影响
                _this.section = {};
                $("#form-modal").modal("show");
            },
            /*
             * 弹出编辑模拟框
             * */
            edit(section) {
                let _this = this;
                // 点击编辑之后, 在文本框中显示section
                // 保证对_this.section变量的修改不会影响到 传递过来的section变量
                _this.section = $.extend({}, section);
                $("#form-modal").modal("show");
            },
            /*
            *  展示小节列表
            * */
            list(page) {
                let _this = this;
                // 加载框
                Loading.show();
                // post 默认用json来向后端传递数据
                // _this.$refs.xxx 获取子组件变量/操作
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/section/list', {
                    page: page,
                    size: _this.$refs.pagination.size,
                }).then((response) => {
                    Loading.hide();
                    console.log("查询到小节结果:", response);
                    let res = response.data;
                    _this.sections = res.content.list;
                    // 将数据渲染到子组件
                    _this.$refs.pagination.render(page, res.content.total);
                })
            },
            /*
            *  新增小节数据
            * */
            save() {
                let _this = this;

                // 进行数据校验, 增加 1!=1, 来去掉最前面的 ||
                if ( 1 != 1
                        || !Validator.require(_this.section.title, "标题")
                        || !Validator.length(_this.section.title, "标题", 1 , 50)
                        || !Validator.length(_this.section.video, "视频", 1 , 200)
                ){
                  return;
                }

                Loading.show();
                // post 默认用json来向后端传递数据
                _this.$ajax.post(process.env.VUE_APP_SERVER  + '/business/admin/section/save', _this.section
                ).then((response) => {
                    Loading.hide();
                    console.log("新增小节结果:", response);
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
            *  删除小节数据
            * */
            del(id) {
                let _this = this;
                // 确认弹出框
                Confirm.show("删除后不可恢复!", function () {
                    Loading.show();
                    // post 默认用json来向后端传递数据
                    // 使用Restful请求在后面直接拼接
                    _this.$ajax.delete(process.env.VUE_APP_SERVER  + '/business/admin/section/delete/' + id
                    ).then((response) => {
                        Loading.hide();
                        console.log("删除小节结果:", response);
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
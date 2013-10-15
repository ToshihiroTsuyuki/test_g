package com.example.test;

import java.util.List;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class TestActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<Object> {
	/**Bundle保存（取出）用KEY*/
	private static final String KEY_URL_STR= "urlStr";
	/**取得用URL*/
	private static final String LOAD_URL = "http://weather.livedoor.com/forecast/rss/earthquake.xml";
	private static final String LOAD_URL2 =
			"https://help.github.com/articles/creating-an-oauth-token-for-command-line-use";
	int i = 1;
	ListView lv;
	@Override
	protected void onCreate(Bundle saved) {
		super.onCreate(saved);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		/*AsyncTaskLoaderに値を渡す為にBundleに情報を入れる*/
		Bundle args = new Bundle(1);
		args.putString(KEY_URL_STR, LOAD_URL);
		//args.putString(KEY_URL_STR, LOAD_URL2);
		getSupportLoaderManager().initLoader(0, args, this);
		/*
		 * AsyncTaskLoaderを初期化後、起動する
		 * 第1引数と第2引数が下記Loaderの引数に渡される。
		 * onCreateLoader() に渡される id の値に応じて
		 * 戻り値にする Loader<T> の インスタンスを切り替えたり、
		 * Loader<T> インスタンスの初期化に必要なパラメーターを argsで渡すことができる
		 * 
		 * */
	}
	/*
	 * Loaderが正しく生成されたときに呼び出される。
	 */
	@Override
	public Loader<Object> onCreateLoader(int id, Bundle args) {
		ProgressDialogFragment dialog = new ProgressDialogFragment();	//プログレスダイアログ表示
		Bundle pa = new Bundle();
		pa.putString("message", "データを読み込んでいます。");
		dialog.setArguments(pa);
		dialog.show(getSupportFragmentManager(), "progress");

		String urlStr = args.getString(KEY_URL_STR);					//KEY情報を基にBundle内のURLを取り出す。
		if (! TextUtils.isEmpty(urlStr)) {
			return new AsyncObjectLoader(getApplication(), urlStr);		//メインアクティビティのContextを渡す。
		}
		return null;
	}

	/*
	 * loader内の処理が終了したときに呼び出される。
	 */
	@Override
	public void onLoadFinished(Loader<Object> loader, Object data2) {
		/*プログレスダイアログを閉じる */
		ProgressDialogFragment dialog = (ProgressDialogFragment)getSupportFragmentManager().findFragmentByTag("progress");
		if (dialog != null) {//ダイアログが存在している時のみ消す
			dialog.onDismiss(dialog.getDialog());
		}
		setContentView(R.layout.zisin);
		//final TextView tx = (TextView)findViewById(R.id.textView1z);

		if (data2 != null) {
			String stdata = data2.toString();
			if (stdata.startsWith("<?xml")) {
				final List<TestItem> zisinItems = TestReader.getZisinList(stdata);
				final StringBuilder st =new StringBuilder();
				final StringBuilder st2 =new StringBuilder();
				final StringBuilder st3 =new StringBuilder();
				final StringBuilder st4 =new StringBuilder();
				final StringBuilder st5 =new StringBuilder();
				final StringBuilder st6 =new StringBuilder();
				final StringBuilder st7 =new StringBuilder();
				final StringBuilder st8 =new StringBuilder();
				final StringBuilder st9 =new StringBuilder();
				final StringBuilder st10 =new StringBuilder();
				
				final StringBuilder lst =new StringBuilder();
				final StringBuilder lst2 =new StringBuilder();
				final StringBuilder lst3 =new StringBuilder();
				final StringBuilder lst4 =new StringBuilder();
				final StringBuilder lst5 =new StringBuilder();
				final StringBuilder lst6 =new StringBuilder();
				final StringBuilder lst7 =new StringBuilder();
				final StringBuilder lst8 =new StringBuilder();
				final StringBuilder lst9 =new StringBuilder();
				final StringBuilder lst10 =new StringBuilder();
				
				i++;
				if(i>=zisinItems.size())i=0;
				st.delete(0,st.length());
				//tx.setText(zTitle[i]+"\n"+zCategory[i]+"\n"+zDescription[i]+"\n");
				//int i=15;
				// タイトルの文字列を追加
				//for (int i = 2; i < 12; ) {
					
					st.append(zisinItems.get(i+1).getCategory()+"\n");
					st.append(zisinItems.get(i).getTitle()+"\n");
					st.append(zisinItems.get(i+1).getDescription()+"\n");
					lst.append(zisinItems.get(i).getLink());
					
					st2.append(zisinItems.get(i+2).getCategory()+"\n");
					st2.append(zisinItems.get(i+1).getTitle()+"\n");
					st2.append(zisinItems.get(i+2).getDescription()+"\n");
					lst2.append(zisinItems.get(i+1).getLink());
					
					st3.append(zisinItems.get(i+3).getCategory()+"\n");
					st3.append(zisinItems.get(i+2).getTitle()+"\n");
					st3.append(zisinItems.get(i+3).getDescription()+"\n");
					lst3.append(zisinItems.get(i+2).getLink());
					
					st4.append(zisinItems.get(i+4).getCategory()+"\n");
					st4.append(zisinItems.get(i+3).getTitle()+"\n");
					st4.append(zisinItems.get(i+4).getDescription()+"\n");
					lst4.append(zisinItems.get(i+3).getLink());
					
					st5.append(zisinItems.get(i+5).getCategory()+"\n");
					st5.append(zisinItems.get(i+4).getTitle()+"\n");
					st5.append(zisinItems.get(i+5).getDescription()+"\n");
					lst5.append(zisinItems.get(i+4).getLink());
					
					st6.append(zisinItems.get(i+6).getCategory()+"\n");
					st6.append(zisinItems.get(i+5).getTitle()+"\n");
					st6.append(zisinItems.get(i+6).getDescription()+"\n");
					lst6.append(zisinItems.get(i+5).getLink());
					
					st7.append(zisinItems.get(i+7).getCategory()+"\n");
					st7.append(zisinItems.get(i+6).getTitle()+"\n");
					st7.append(zisinItems.get(i+7).getDescription()+"\n");
					lst7.append(zisinItems.get(i+6).getLink());
				
					st8.append(zisinItems.get(i+8).getCategory()+"\n");
					st8.append(zisinItems.get(i+7).getTitle()+"\n");
					st8.append(zisinItems.get(i+8).getDescription()+"\n");
					lst8.append(zisinItems.get(i+7).getLink());
				
					st9.append(zisinItems.get(i+9).getCategory()+"\n");
					st9.append(zisinItems.get(i+8).getTitle()+"\n");
					st9.append(zisinItems.get(i+9).getDescription()+"\n");
					lst9.append(zisinItems.get(i+8).getLink());
				
					st10.append(zisinItems.get(i+10).getCategory()+"\n");
					st10.append(zisinItems.get(i+9).getTitle()+"\n");
					st10.append(zisinItems.get(i+10).getDescription()+"\n");
					lst10.append(zisinItems.get(i+9).getLink());
				
					//tx.setText(st);
					final String a = new String(st);
					final String b = new String(st2);
					final String c = new String(st3);
					final String d = new String(st4);
					final String e = new String(st5);
					final String f = new String(st6);
					final String g = new String(st7);
					final String h = new String(st8);
					final String i2 = new String(st9);
					final String j = new String(st10);
					
					final String l_a = new String(lst);
					final String l_b = new String(lst2);
					final String l_c = new String(lst3);
					final String l_d = new String(lst4);
					final String l_e = new String(lst5);
					final String l_f = new String(lst6);
					final String l_g = new String(lst7);
					final String l_h = new String(lst8);
					final String l_i2 = new String(lst9);
					final String l_j = new String(lst10);
					
					String[] members = {a,b,c,d,e,f,g,h,i2,j};
					i++;
				
				
		        lv = (ListView)this.findViewById(R.id.listView1);
		        
		        ArrayAdapter<String> adapter
		           = new ArrayAdapter<String>(this, R.layout.list_text, members);
		        lv.setAdapter(adapter);
				//}
		        //lv.setSelection(1);
		        //lv.setTextFilterEnabled(true);


		        
		        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		            @Override
		            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		                ListView listView = (ListView) parent;
		                String item = (String) listView.getItemAtPosition(position);
		                	//Toast.makeText(getApplicationContext(), item + " clicked",
		                 //       Toast.LENGTH_LONG).show();
		                	
		            }
		        });
		        lv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		            //リスト項目が選択された時の処理
		                @Override
		                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		                    ListView listView = (ListView) parent;
		                    String item = (String) listView.getItemAtPosition(position);
		                   // Toast.makeText(getApplicationContext(), item + " selected",
		                     //       Toast.LENGTH_LONG).show();
		                }
		                //リスト項目がなにも選択されていない時の処理
		                @Override
		                public void onNothingSelected(AdapterView<?> parent) {
		                  //  Toast.makeText(getApplicationContext(), "no item selected",
		                         //   Toast.LENGTH_LONG).show();
		                    }
		            });
		     
		            //リスト項目が長押しされた時の処理
		            lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
		                @Override
		                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
		                    ListView listView = (ListView) parent;
		                    String item = (String) listView.getItemAtPosition(position);
		                    if(item == a){
		                    	Uri uri = Uri.parse(l_a);
		        				Intent i = new Intent(Intent.ACTION_VIEW,uri);
		        				startActivity(i);
			                }else if(item == b){
			                	Uri uri = Uri.parse(l_b);
		        				Intent i = new Intent(Intent.ACTION_VIEW,uri);
		        				startActivity(i);
			                }else if(item == c){
			                	Uri uri = Uri.parse(l_c);
		        				Intent i = new Intent(Intent.ACTION_VIEW,uri);
		        				startActivity(i);
			                }else if(item == d){
			                	Uri uri = Uri.parse(l_d);
		        				Intent i = new Intent(Intent.ACTION_VIEW,uri);
		        				startActivity(i);
			                }else if(item == e){
			                	Uri uri = Uri.parse(l_e);
		        				Intent i = new Intent(Intent.ACTION_VIEW,uri);
		        				startActivity(i);
			                }else if(item == f){
			                	Uri uri = Uri.parse(l_f);
		        				Intent i = new Intent(Intent.ACTION_VIEW,uri);
		        				startActivity(i);
			                }else if(item == g){
			                	Uri uri = Uri.parse(l_g);
		        				Intent i = new Intent(Intent.ACTION_VIEW,uri);
		        				startActivity(i);
			                }else if(item == h){
			                	Uri uri = Uri.parse(l_h);
		        				Intent i = new Intent(Intent.ACTION_VIEW,uri);
		        				startActivity(i);
			                }else if(item == i2){
			                	Uri uri = Uri.parse(l_i2);
		        				Intent i = new Intent(Intent.ACTION_VIEW,uri);
		        				startActivity(i);
			                }else if(item == j){
			                	Uri uri = Uri.parse(l_j);
		        				Intent i = new Intent(Intent.ACTION_VIEW,uri);
		        				startActivity(i);
			                }
		                    
		                    return false;
		                }
		            });
				
		            
					Button bt2 = (Button)findViewById(R.id.button2);
					bt2.setOnClickListener(new ButtonClickListener());
					
			}
			/*JSON解析部*/
			Log.d("JSONObject",String.valueOf(data2));
		}	
	}
	class ButtonClickListener implements OnClickListener{
		public void onClick(View v) {
			if(v.getId() == R.id.button2){
				Uri uri = Uri.parse("http://weather.livedoor.com/warn/");
				Intent i = new Intent(Intent.ACTION_VIEW,uri);
				startActivity(i);
			}
		}   	
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if(keyCode == KeyEvent.KEYCODE_BACK){
	    	Intent buttonIntent11 = new Intent(getApplicationContext(),MainActivity.class);  
			buttonIntent11.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
			startActivity(buttonIntent11);
			finish();
	        return super.onKeyDown(keyCode, event);
	        
	    }else{
	        return false;
	    }
	}
	/*
	 *　loaderがリセットされた時に呼び出される。
	 */
	@Override
	public void onLoaderReset(Loader<Object> data2) {
		// 特に何もしない
	}
}	